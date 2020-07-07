package com.wuzz.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/7/2 19:43
 * @since 1.0
 **/
@Configuration
@EnableResourceServer
public class WuzzResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //配置受保护的资源
        http.authorizeRequests().antMatchers("/api/order/**").authenticated();
    }
}
