package com.wuzz.demo;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/6 15:57
 * @since 1.0
 **/
@Configuration
public class NacosRegisterConfiguration {

    @Value("${server.port}")
    private int serverPort;

    @Value("${spring.application.name}")
    private String applicationName;

    @NacosInjected
    private NamingService namingService;

    //restTemplate.getForObject("http://nacos-provider/get", String.class); 调用
    @PostConstruct
    public void registerInstance() throws NacosException {
        namingService.registerInstance(applicationName, "127.0.0.1", serverPort, "DEFAULT");
    }
}

