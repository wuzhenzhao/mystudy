package com.wuzz.demo.protocol;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Exporter;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.RpcException;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/8/16
 * Time: 15:36
 * Description 描述:
 */
public class MyProtocol implements Protocol {
    @Override
    public int getDefaultPort() {
        return 9999;
    }

    @Override
    public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
        return null;
    }

    @Override
    public <T> Invoker<T> refer(Class<T> aClass, URL url) throws RpcException {
        return null;
    }

    @Override
    public void destroy() {

    }
}
