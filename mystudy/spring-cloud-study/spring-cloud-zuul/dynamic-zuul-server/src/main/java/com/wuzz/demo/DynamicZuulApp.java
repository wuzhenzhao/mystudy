package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@SpringBootApplication
@EnableZuulProxy
public class DynamicZuulApp {
    private final static Logger log = LoggerFactory.getLogger(DynamicZuulApp.class);

    public static void main(String[] args) {
        SpringApplication.run(DynamicZuulApp.class, args);
        log.info("服务启动成功");
    }

    @RefreshScope//Zuul的配置内容动态化
    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }
}
