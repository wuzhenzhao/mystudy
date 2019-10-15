package com.wuzz.demo.auto.configuration.demo.configuration;


/**
 * <bean name="" class="com.wuzz.demo.auto.configuration.demo.configuration"/>
 * javaconfig
 * autoconfiguration  (简化我们bean的注入逻辑）
 * //希望这个类被spring 托管
 */
public class DemoClass {

    public void say(){
        System.out.println("Say: Hello Wuzz");
    }
}


