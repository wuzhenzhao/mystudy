package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/4/22 17:40
 * @since 1.0
 **/
@SpringBootApplication
public class SenderApp {
    private final static Logger log = LoggerFactory.getLogger(SenderApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SenderApp.class, args);
        log.info("服务启动成功 ");

    }
}
