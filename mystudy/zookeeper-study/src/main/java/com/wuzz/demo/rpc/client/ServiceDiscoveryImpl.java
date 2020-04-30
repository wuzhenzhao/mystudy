package com.wuzz.demo.rpc.client;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/30 14:11
 * @since 1.0
 **/
public class ServiceDiscoveryImpl implements IServiceDiscovery {
    // 从指定节点下获取的子节点列表
    List<String> repos = new ArrayList<>();
    // 服务器连接地址，就是服务端的 ZkConfig.CONNNECTION_STR
    private String address;

    private CuratorFramework curatorFramework;

    //注册根节点
    public final static String ZK_REGISTER_PATH = "/registrys";

    // 为了方便测试，这里直接再构造里面启动连接
    public ServiceDiscoveryImpl(String address) {
        this.address = address;

        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(address).
                sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000,
                        10)).build();
        curatorFramework.start();
    }

    @Override // 本质就是获取指定服务节点下的子节点
    public String discover(String serviceName) {
        String path = ZK_REGISTER_PATH + "/" + serviceName;
        try {
            repos = curatorFramework.getChildren().forPath(path);

        } catch (Exception e) {
            throw new RuntimeException("获取子节点异常：" + e);
        }
        //动态发现服务节点的变化
        registerWatcher(path);

        //简单负载均衡机制
        if (repos == null || repos.size() == 0) {
            return null;
        }
        if (repos.size() == 1) {
            return repos.get(0);
        }

        int len = repos.size();
        Random random = new Random();
        return repos.get(random.nextInt(len));//返回调用的服务地址
    }

    // 这里是之前提到的用 curator 客户端进行时间注册的操作
    private void registerWatcher(final String path) {
        PathChildrenCache childrenCache = new PathChildrenCache
                (curatorFramework, path, true);

        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                repos = curatorFramework.getChildren().forPath(path);
            }
        };
        childrenCache.getListenable().addListener(pathChildrenCacheListener);
        try {
            childrenCache.start();
        } catch (Exception e) {
            throw new RuntimeException("注册PatchChild Watcher 异常" + e);
        }


    }
}
