package com.wuzz.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/8/10 19:59
 * @since 1.0
 **/
@MapperScan("com.wuzz.demo.dao")
@SpringBootApplication
public class ShardingBootStudyApp {
    private final static Logger log = LoggerFactory.getLogger(ShardingBootStudyApp.class);

    public static void main(String[] args) {
        SpringApplication.run(ShardingBootStudyApp.class, args);
        log.info("服务启动成功 ");

    }
}
