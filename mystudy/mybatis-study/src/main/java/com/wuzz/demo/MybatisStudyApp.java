package com.wuzz.demo;

import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan("com.wuzz.demo.dao")
@SpringBootApplication
public class MybatisStudyApp {
    private final static Logger log = LoggerFactory.getLogger(MybatisStudyApp.class);

    public static void main(String[] args) {
        SpringApplication.run(MybatisStudyApp.class, args);
        log.info("服务启动成功");

    }
}
