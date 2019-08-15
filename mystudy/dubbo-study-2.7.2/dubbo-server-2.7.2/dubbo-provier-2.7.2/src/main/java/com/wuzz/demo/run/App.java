package com.wuzz.demo.run;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
public class App {

    public static void main(String[] args)throws IOException {
        ClassPathXmlApplicationContext context=
                new ClassPathXmlApplicationContext
                        ("META-INF/spring/dubbo-server.xml");
        context.start();
        System.out.println("dubbo服务启动。。。");
        System.in.read(); //阻塞当前进程


    }
}
