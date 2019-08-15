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
public class AppCluster2 {
//    private final static Logger log = LoggerFactory.getLogger(NettyApp.class);

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context=
                new ClassPathXmlApplicationContext
                        ("META-INF/spring/dubbo-cluster2.xml");
        context.start();

        System.in.read(); //阻塞当前进程
    }
}
