package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@SpringBootApplication
@ServletComponentScan // 先注释掉，避免mvc的影响
public class SpringStudyApp {
    private final static Logger log = LoggerFactory.getLogger(SpringStudyApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringStudyApp.class, args);
        log.info("服务启动成功");

    }

}
