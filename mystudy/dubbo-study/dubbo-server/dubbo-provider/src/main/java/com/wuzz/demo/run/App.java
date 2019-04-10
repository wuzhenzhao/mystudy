package com.wuzz.demo.run;

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
public class App {
//    private final static Logger log = LoggerFactory.getLogger(NettyApp.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
//        log.info("服务启动成功");

    }
}
