package com.wuzz.demo.rpc.client;

import com.wuzz.demo.rpc.server.HelloService;

public class ClientTest {

    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();

        HelloService hello = rpcClientProxy.clientProxy
                (HelloService.class, "localhost", 6666);
        System.out.println(hello.sayHello("wuzz"));
    }
}
