package com.wuzz.demo.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

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

    public static void main(String[] args) throws IOException {
//        SpringApplication.run(App.class,args);
//        log.info("服务启动成功");
        //默认情况下会使用spring容器来启动服务
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext
                        ("META-INF/spring/dubbo-server.xml");
        context.start();
        System.in.read(); //阻塞当前进程


    }
}
