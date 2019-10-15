package com.wuzz.demo.auto.configuration.demo.importdemo.currentpackage;

import com.wuzz.demo.auto.configuration.demo.importdemo.otherpackage.OtherConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(OtherConfig.class)
@Configuration
public class SpringConfig {

    @Bean
    public DefaultBean defaultBean(){
        return new DefaultBean();
    }
}
