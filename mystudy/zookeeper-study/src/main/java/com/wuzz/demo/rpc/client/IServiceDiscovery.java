package com.wuzz.demo.rpc.client;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/30 14:10
 * @since 1.0
 **/
public interface IServiceDiscovery {
    /**
     * 根据请求的服务地址，获得对应的调用地址
     * @param serviceName
     * @return
     */
    String discover(String serviceName);
}
