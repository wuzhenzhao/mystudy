package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulRibbonApp {
    private final static Logger log = LoggerFactory.getLogger(ConsulRibbonApp.class);

    public static void main(String[] args) {
        SpringApplication.run(ConsulRibbonApp.class, args);
        log.info("服务启动成功");

    }

    @Bean
    @LoadBalanced // ribbon是客户端 的负载均衡工具
    //默认算法是轮询算法 核心组件IRule
    public RestTemplate getRestTemplate() {

        return new RestTemplate();
    }
}
