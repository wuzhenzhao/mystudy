package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/4/23 14:22
 * @since 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class Trace2App {
    private final static Logger log = LoggerFactory.getLogger(Trace2App.class);

    public static void main(String[] args) {
        SpringApplication.run(Trace2App.class,args);
        log.info("服务启动成功");

    }
}
