package com.wuzz.demo.auto.configuration.demo.importselector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDefineService(exclude = CacheService.class)
public class EnableDemoMain {

//    public static void main(String[] args) {
//        ConfigurableApplicationContext ca = SpringApplication.run(EnableDemoMain.class, args);
////        CacheService bean = ca.getBean(CacheService.class);
//
////        System.out.println(ca.getBean(CacheService.class));
////        System.out.println(ca.getBean(LoggerService.class));
//        CacheService cacheService = (CacheService)ca.getBean("com.wuzz.demo.auto.configuration.demo.importselector.CacheService");
//        System.out.println(cacheService.login());
////        System.out.println(ca.getBean(GupaoCore.class).study());
//    }


}
