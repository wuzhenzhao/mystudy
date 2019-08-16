package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/8/16
 * Time: 14:13
 * Description 描述:
 */
@SpringBootApplication
public class SpringBootDubboClientApp {

    private final static Logger log = LoggerFactory.getLogger(SpringBootDubboClientApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDubboClientApp.class, args);
        log.info("服务启动成功");

    }
}
