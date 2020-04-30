package com.wuzz.demo.demo;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/5
 * Time: 20:16
 * Description:
 * ClassPath:com.wuzz.demo.demo.LeaderLatchTest
 */
//Curator提供了两种选举方案：Leader Latch 和 Leader Election。下面分别介绍这两种选举方案。
//Leader Latch
//使用 Leader Latch 方案进行Master选举，系统将随机从候选者中选出一台作为 leader，
// 直到调用 close() 释放leadship，此时再重新随机选举 leader，否则其他的候选者无法成为 leader。

//下面的程序将启动 N 个线程用来模拟分布式系统中的节点，
// 每个线程将创建一个Zookeeper客户端和一个 LeaderLatch 对象用于选举；
// 每个线程有一个名称，名称中有一个编号用于区分；每个线程的存活时间为 number * 10秒 ，
// 存活时间结束后将关闭 LeaderLatch 对象和客户端，
// 表示该 ‘节点’ 宕机，如果该节点为 Master节点，这时系统将重新发起 Master选举。
public class LeaderLatchTest {
    private static final String zkServerIps = "192.168.1.101:2181";
    private static final String masterPath = "/testZK/leader_latch";

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        try {//启动5个线程
            for (int i = 0; i < 5; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 创建客户端
                        CuratorFramework client = getClient();
                        try {
                            client.blockUntilConnected();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(client.getState());
                        int number = atomicInteger.getAndIncrement();
                        final LeaderLatch latch = new LeaderLatch(client, masterPath, "client#" + number);
                        System.out.println("创建客户端：" + latch.getId());
                        // LeaderLatch 添加监听事件
                        latch.addListener(new LeaderLatchListener() {
                            @Override//抢主成功时触发
                            public void isLeader() {
                                System.out.println(latch.getId() + ": 我现在被选举为Leader！我开始工作了....");
                                try {//放弃leader。让剩下来的服务重新选主
                                    latch.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override//不会执行
                            public void notLeader() {
                            }
                        });
                        try {
                            latch.start();
                            System.in.read();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {
                            System.out.println("客户端 " + latch.getId() + " 关闭");
                            CloseableUtils.closeQuietly(latch);
                            CloseableUtils.closeQuietly(client);
                        }
                    }
                }).start();
            }
//            countDownLatch.await(); // 等待，只有所有线程都退出
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static synchronized CuratorFramework getClient() {
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(zkServerIps)
                .sessionTimeoutMs(6000).connectionTimeoutMs(3000) //.namespace("LeaderLatchTest")
                .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        client.start();
        return client;
    }
}
