package com.wuzz.demo;

import com.wuzz.demo.service.HelloService;
import com.wuzz.demo.service.UserService;
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

        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("META-INF/spring/dubbo-client.xml");
        for(int i=0;i<11;i++) {
            // 得到IGpHello的远程代理对象
//            HelloService helloService = (HelloService) context1.getBean("helloService");
            UserService userService = (UserService) context1.getBean("userService");
//            System.out.println(helloService.sayHello("WUZZ"));
            System.out.println(userService.register(i));
            Thread.sleep(1000);
        }
        System.in.read();
    }
}
