package com.wuzz.demo.rpc.server;

public class ServerTest {

    public static void main(String[] args) {
        HelloService helloService  =new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(helloService, 6666);
    }
}
