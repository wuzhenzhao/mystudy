package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@EnableEurekaClient
@SpringBootApplication
public class EurekaServerProvider8001_App {
    private final static Logger log = LoggerFactory.getLogger(EurekaServerProvider8001_App.class);

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerProvider8001_App.class,args);
        log.info("服务启动成功");

    }
}
