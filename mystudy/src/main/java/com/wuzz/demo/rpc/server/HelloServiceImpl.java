package com.wuzz.demo.rpc.server;

public class HelloServiceImpl implements HelloService {

    public String sayHello(String msg) {
        return msg+"hello world";
    }
}
