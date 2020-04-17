package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@SpringBootApplication
public class ConfigClientApp {
    private final static Logger log = LoggerFactory.getLogger(ConfigClientApp.class);

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApp.class,args);
        log.info("服务启动成功");

    }
}
