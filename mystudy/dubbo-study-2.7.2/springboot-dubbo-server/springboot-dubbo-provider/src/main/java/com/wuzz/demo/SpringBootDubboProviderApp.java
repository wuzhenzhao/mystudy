package com.wuzz.demo;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/8/16
 * Time: 14:13
 * Description 描述:
 */
@DubboComponentScan(basePackages = "com.wuzz.demo") //dubbo服务扫描
@SpringBootApplication
public class SpringBootDubboProviderApp {

    private final static Logger log = LoggerFactory.getLogger(SpringBootDubboProviderApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDubboProviderApp.class, args);
        log.info("服务启动成功");

    }

//    public Object invokeMethod(Object o, String n, Class[] p, Object[] v) throws java.lang.reflect.InvocationTargetException {
//        com.wuzz.demo.api.HelloService w;
//        try {
//            w = ((com.wuzz.demo.api.HelloService) $1);
//        } catch (Throwable e) {
//            throw new IllegalArgumentException(e);
//        }
//        try {
//            if ("sayHello".equals($2) && $3.length == 0) {
//                return ($w) w.sayHello();
//            }
//        } catch (Throwable e) {
//            throw new java.lang.reflect.InvocationTargetException(e);
//        }
//        throw new org.apache.dubbo.common.bytecode.NoSuchMethodException("Not found method \"" + $2 + "\" in class com.wuzz.demo.api.HelloService.");
//    }
}
