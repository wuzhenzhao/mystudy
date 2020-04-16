package com.wuzz.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@EnableTurbine
@EnableDiscoveryClient
@SpringBootApplication
public class TurbineApp {

    public static void main(String[] args) {
        SpringApplication.run(TurbineApp.class, args);

    }
}
