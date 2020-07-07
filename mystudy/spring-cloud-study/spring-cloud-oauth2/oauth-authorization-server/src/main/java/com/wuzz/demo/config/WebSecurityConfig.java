package com.wuzz.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/7/3 19:20
 * @since 1.0
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").fullyAuthenticated().and().httpBasic();

    }
}
