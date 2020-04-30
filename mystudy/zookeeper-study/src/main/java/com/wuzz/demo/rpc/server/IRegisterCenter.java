package com.wuzz.demo.rpc.server;

/**
 * @description: 服务注册接口
 * @author: wuzhenzhao
 * @time 2020/4/30 11:43
 * @since 1.0
 **/
public interface IRegisterCenter {
    /**
     * 注册服务名称和服务地址
     * @param serviceName
     * @param serviceAddress
     */
    void register(String serviceName,String serviceAddress);
}
