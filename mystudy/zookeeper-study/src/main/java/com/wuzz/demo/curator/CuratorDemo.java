package com.wuzz.demo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/5/5
 * Time: 14:13
 * Description 描述:
 */
public class CuratorDemo {

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder() // 获取构造器
                .connectString("192.168.1.101:2181," + "192.168.1.102:2181,192.168.1.103:2181") // 集群地址
                .sessionTimeoutMs(4000) // session超时事件
                .retryPolicy(new ExponentialBackoffRetry(1000, 3)) // 重试机制
                .namespace("curator") // 命名构建 根节点
                .build(); // 构造

        curatorFramework.start();// 启动 连接
        curatorFramework.blockUntilConnected();
        // 原生api中，必须是逐层创建，也就是父节点必须存在，子节点才能创建
        String path = curatorFramework.create() // 创建节点
                .creatingParentsIfNeeded() // 如果需要创建父节点
                .withMode(CreateMode.PERSISTENT) // 选择节点类型
                .forPath("/wuzz/node1","1".getBytes()); // 路径及值
        System.out.println(path);

        Stat stat = new Stat();
        curatorFramework.getData() // 获取
                .storingStatIn(stat).forPath("/wuzz/node1");
        System.out.println(stat);

        curatorFramework.setData() // 修改
                .withVersion(stat.getVersion()).forPath("/wuzz/node1", "xx".getBytes());

        curatorFramework.delete()// 删除
                .deletingChildrenIfNeeded().forPath("/wuzz/node1");

        curatorFramework.close();// 关闭连接

    }
}
