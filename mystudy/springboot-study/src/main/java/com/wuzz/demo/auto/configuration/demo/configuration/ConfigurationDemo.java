package com.wuzz.demo.auto.configuration.demo.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationDemo {

    @Bean
    public DemoClass demoClass(){
        return new DemoClass();
    }

}
