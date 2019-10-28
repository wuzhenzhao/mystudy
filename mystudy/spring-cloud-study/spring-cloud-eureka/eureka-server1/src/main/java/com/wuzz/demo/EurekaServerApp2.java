package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@EnableEurekaServer // Eureka服务端注解
@SpringBootApplication
public class EurekaServerApp2 {
    private final static Logger log = LoggerFactory.getLogger(EurekaServerApp2.class);

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApp2.class,args);
        log.info("服务启动成功");

    }
}
