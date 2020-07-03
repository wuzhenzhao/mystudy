package com.wuzz.demo.oauth.config;

import com.wuzz.demo.oauth.MyAuthenctiationFailureHandler;
import com.wuzz.demo.oauth.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
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
    //http://localhost:8889/oauth/authorize?client_id=wuzzClientId&response_type=code&redirect_uri=http://www.wuzz.demo&scope=all

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("http://localhost:8080/#/login")//自定义登陆地址
                .loginProcessingUrl("/authentication/form") //登录处理地址
                .successHandler(myAuthenticationSuccessHandler) // 登陆成功处理器
                .failureHandler(myAuthenctiationFailureHandler) // 登陆失败处理器
                ;
        http.authorizeRequests()
//                .antMatchers("/authentication/form").permitAll()
                .anyRequest().authenticated()
            .and()
                .csrf().disable();
    }
}
