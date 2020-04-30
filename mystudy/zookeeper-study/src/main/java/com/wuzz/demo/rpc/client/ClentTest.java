package com.wuzz.demo.rpc.client;

import com.wuzz.demo.rpc.server.HelloService;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/30 14:15
 * @since 1.0
 **/
public class ClentTest {
    public static void main(String[] args) throws InterruptedException {
        IServiceDiscovery serviceDiscovery = new
                ServiceDiscoveryImpl("192.168.1.101:2181");

        RpcClientProxy rpcClientProxy = new RpcClientProxy(serviceDiscovery);

        HelloService hello = rpcClientProxy.clientProxy(HelloService.class);
        System.out.println(hello.sayHello("wuzz"));
    }
}
