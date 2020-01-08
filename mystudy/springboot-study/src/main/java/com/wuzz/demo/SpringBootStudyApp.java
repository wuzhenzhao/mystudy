package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@EnableSwagger2
@SpringBootApplication
@EnableScheduling
public class SpringBootStudyApp {
    private final static Logger log = LoggerFactory.getLogger(SpringBootStudyApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyApp.class, args);
        log.info("服务启动成功 ");

    }
}
