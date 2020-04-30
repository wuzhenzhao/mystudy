package com.wuzz.demo.rpc.server;

import java.io.IOException;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/30 13:53
 * @since 1.0
 **/
public class ServerTest {
    public static void main(String[] args) throws IOException {
        HelloService helloService=new HelloServiceImpl();
        IRegisterCenter registerCenter=new RegisterCenterImpl();

        RpcServer rpcServer=new RpcServer(registerCenter,"127.0.0.1:8080");
        rpcServer.bind(helloService);
        rpcServer.publisher();
        System.in.read();
    }

}
