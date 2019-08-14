package com.wuzz.demo;

import com.wuzz.demo.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/8/13
 * Time: 16:59
 * Description 描述:
 */
public class App {

    public static void main(String[] args) throws IOException, InterruptedException {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/dubbo-client.xml");
//        // 得到IGpHello的远程代理对象
//        HelloService iGpHello = (HelloService) context.getBean("helloService");
//        System.out.println(iGpHello.sayHello("WUZZ"));
//        Thread.sleep(1000);
//        System.in.read();


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/dubbo-client.xml");

        for(int i=0;i<10;i++) {
            // 得到IGpHello的远程代理对象
            HelloService helloService = (HelloService) context.getBean("helloService");

            System.out.println(helloService.sayHello("WUZZ"));
            Thread.sleep(1000);
        }
        System.in.read();
    }
}
