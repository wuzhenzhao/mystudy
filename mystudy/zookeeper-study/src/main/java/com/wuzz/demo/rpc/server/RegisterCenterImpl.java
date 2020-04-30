package com.wuzz.demo.rpc.server;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @description: 服务注册执行类
 * @author: wuzhenzhao
 * @time 2020/4/30 13:43
 * @since 1.0
 **/
public class RegisterCenterImpl implements IRegisterCenter {

    //zk链接地址
    public final static String CONNNECTION_STR = "192.168.1.101:2181";

    //注册根节点
    public final static String ZK_REGISTER_PATH = "/registrys";

    private CuratorFramework curatorFramework;

    {// 这段代码无非是连接服务器，自己看着写在哪里把
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(CONNNECTION_STR). //
                sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000,
                        10)).build();
        curatorFramework.start();
    }

    @Override
    public void register(String serviceName, String serviceAddress) {
        //注册相应的服务
        String servicePath = ZK_REGISTER_PATH + "/" + serviceName;

        try {
            //判断 /registrys/product-service是否存在，不存在则创建
            if (curatorFramework.checkExists().forPath(servicePath) == null) {
                curatorFramework.create().creatingParentsIfNeeded().
                        withMode(CreateMode.PERSISTENT).forPath(servicePath, "0".getBytes());
            }
            // 组装节点地址
            String addressPath = servicePath + "/" + serviceAddress;
            String rsNode = curatorFramework.create().withMode(CreateMode.EPHEMERAL).
                    forPath(addressPath, "0".getBytes());
            System.out.println("服务注册成功：" + rsNode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
