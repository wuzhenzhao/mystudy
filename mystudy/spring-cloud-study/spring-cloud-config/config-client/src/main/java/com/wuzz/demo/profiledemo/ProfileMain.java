package com.wuzz.demo.profiledemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
public class ProfileMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");
        context.register(ProfileConfiguration.class);
        context.refresh();
        System.out.println(context.getBean(ProfileService.class));
    }
}
