package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@SpringBootApplication
@EnableHystrixDashboard //开启仪表盘图形化监控的注解
public class HystrixDasboardApp {
    private final static Logger log = LoggerFactory.getLogger(HystrixDasboardApp.class);

    public static void main(String[] args) {
        SpringApplication.run(HystrixDasboardApp.class,args);
        log.info("服务启动成功");

    }
}
