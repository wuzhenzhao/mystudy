package com.wuzz.demo.rpc.client;

import com.wuzz.demo.rpc.server.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/30 14:13
 * @since 1.0
 **/
public class RemoteInvocationHandler implements InvocationHandler {
    private IServiceDiscovery serviceDiscovery;


    public RemoteInvocationHandler(IServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //组装请求
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);

        String serviceAddress = serviceDiscovery.discover(request.getClassName()); //根据接口名称得到对应的服务地址
        //通过tcp传输协议进行传输
        TCPTransport tcpTransport = new TCPTransport(serviceAddress);
        //发送请求
        return tcpTransport.send(request);
    }
}
