package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ZuulApp {
    private final static Logger log = LoggerFactory.getLogger(ZuulApp.class);

    public static void main(String[] args) {
        SpringApplication.run(ZuulApp.class,args);
        log.info("服务启动成功");

    }
}
