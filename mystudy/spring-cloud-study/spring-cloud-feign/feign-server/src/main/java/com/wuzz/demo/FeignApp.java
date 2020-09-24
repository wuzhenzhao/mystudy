package com.wuzz.demo;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.wuzz.demo"})
@EnableCircuitBreaker // 对Hystrix熔断机制的支持
public class FeignApp {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    public static void main(String[] args) {
        SpringApplication.run(FeignApp.class, args);
    }
}
