package com.wuzz.demo.rpc;

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
public class RpcApp {
    private final static Logger log = LoggerFactory.getLogger(RpcApp.class);

    public static void main(String[] args) {
        SpringApplication.run(RpcApp.class,args);
        log.info("服务启动成功");

    }
}
