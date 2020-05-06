package com.wuzz.demo;

import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/5/5
 * Time: 16:51
 * Description 描述:
 */
@SpringBootApplication
@EnableNacosDiscovery
public class NacosApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(NacosApplication.class, args);
    }


}


