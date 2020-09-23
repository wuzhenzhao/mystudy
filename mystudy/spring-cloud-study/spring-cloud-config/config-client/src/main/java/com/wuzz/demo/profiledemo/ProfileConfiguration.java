package com.wuzz.demo.profiledemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@Configuration
public class ProfileConfiguration {

    @Profile("dev")
    @Bean
    public ProfileService profileServiceDev(){
        return new ProfileService("dev");
    }

    @Profile("prd")
    @Bean
    public ProfileService profileServicePrd(){
        return new ProfileService("prd");
    }
}
