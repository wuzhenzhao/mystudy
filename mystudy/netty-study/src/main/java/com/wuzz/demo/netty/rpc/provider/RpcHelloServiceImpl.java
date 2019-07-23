package com.wuzz.demo.netty.rpc.provider;

import com.wuzz.demo.netty.rpc.api.IRpcHelloService;

public class RpcHelloServiceImpl implements IRpcHelloService {

    public String hello(String name) {
        return "Hello " + name + "!";
    }

}  
