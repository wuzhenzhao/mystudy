package com.wuzz.demo.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/5
 * Time: 20:20
 * Description:
 * ClassPath:com.wuzz.demo.demo.LeaderSelectorTest
 */
//Leader Election
//通过 Leader Election 选举方案进行 Master选举，需添加 LeaderSelectorListener 监听器对领导权进行控制，当节点被选为leader之后，
//        将调用 takeLeadership 方法进行业务逻辑处理，处理完成会立即释放 leadship，重新进行Master选举，这样每个节点都有可能成为
//        leader。autoRequeue() 方法的调用确保此实例在释放领导权后还可能获得领导权
public class LeaderSelectorTest {
    private static final String zkServerIps = "192.168.1.101:2181";
    private static final String masterPath = "/testZK/leader_selector";

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        try {
            for (int i = 0; i < 5; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        CuratorFramework client = getClient();  // 创建客户端
                        try {
                            client.blockUntilConnected();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int number = atomicInteger.getAndIncrement();
                        final String name = "client#" + number;
                        final LeaderSelector selector = new LeaderSelector(client, masterPath, new LeaderSelectorListener() {
                            @Override
                            public void takeLeadership(CuratorFramework client) throws Exception {
                                System.out.println(name + ": 我现在被选举为Leader！我开始工作了....");
                                Thread.sleep(3000);
                            }
                            @Override
                            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                            }
                        });
                        System.out.println("创建客户端：" + name);
                        try {//在抢到leader权限并释放后，自动加入抢主队列，重新抢主
                            selector.autoRequeue();
                            selector.start();
                            // 随机等待 number * 10秒，之后关闭客户端
                            System.in.read();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {
                            System.out.println("客户端 " + name + " 关闭");
                            CloseableUtils.closeQuietly(selector);
                            if (!client.getState().equals(CuratorFrameworkState.STOPPED)) {
                                CloseableUtils.closeQuietly(client);
                            }
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static synchronized CuratorFramework getClient() {
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(zkServerIps)
                .sessionTimeoutMs(10000).connectionTimeoutMs(10000) //.namespace("LeaderLatchTest")
                .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        client.start();
        return client;
    }
}
