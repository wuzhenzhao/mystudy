package com.wuzz.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/7/3 19:20
 * @since 1.0
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // 自定义  token 分发类型
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").fullyAuthenticated().and().httpBasic();

//        http.formLogin().permitAll()
////                .loginPage("http://localhost:8081/#/login")//自定义登陆地址
////                .loginProcessingUrl("/authentication/form") //登录处理地址
//                .successHandler(myAuthenticationSuccessHandler) // 登陆成功处理器
//                .failureHandler(myAuthenctiationFailureHandler) // 登陆失败处理器
//                ;
    }
}
