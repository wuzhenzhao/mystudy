package com.wuzz.demo;

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
public class Trace1App {

    public static void main(String[] args) {
        SpringApplication.run(Trace1App.class, args);

    }
}
