package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/4/23 14:22
 * @since 1.0
 **/
@SpringBootApplication
@EnableZipkinServer
public class ZikpinApp {
    private final static Logger log = LoggerFactory.getLogger(ZikpinApp.class);

    public static void main(String[] args) {
        SpringApplication.run(ZikpinApp.class, args);
        log.info("服务启动成功");

    }
}
