package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaServerProviderApp2 {
    private final static Logger log = LoggerFactory.getLogger(EurekaServerProviderApp2.class);

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerProviderApp2.class,args);
        log.info("服务启动成功");

    }
}
