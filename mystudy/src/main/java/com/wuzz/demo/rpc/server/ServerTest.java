package com.wuzz.demo.rpc.server;

public class ServerTest {

    public static void main(String[] args) {
        HelloService iGpHello = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(iGpHello, 6666);
    }
}
