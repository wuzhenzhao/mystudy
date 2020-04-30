package com.wuzz.demo.rpc.server;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/30 13:52
 * @since 1.0
 **/
@RpcAnnotation(HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String msg) {
        return " RPC Hello， " + msg;
    }
}
