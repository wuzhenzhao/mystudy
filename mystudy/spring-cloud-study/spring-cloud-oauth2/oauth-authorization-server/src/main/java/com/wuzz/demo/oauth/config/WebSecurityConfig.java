package com.wuzz.demo.oauth.config;

import com.wuzz.demo.oauth.MyAuthenctiationFailureHandler;
import com.wuzz.demo.oauth.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/7/3 19:20
 * @since 1.0
 **/
@Configuration
@EnableWebSecurity// 开启Security
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 自定义  token 分发类型
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http
//                .httpBasic().and()
//                .formLogin().permitAll()
//                .loginPage("http://localhost:8081/#/login")//自定义登陆地址
//                .loginProcessingUrl("/authentication/form") //登录处理地址
//                .successHandler(myAuthenticationSuccessHandler) // 登陆成功处理器
//                .failureHandler(myAuthenctiationFailureHandler) // 登陆失败处理器
//                .and()
//                .authorizeRequests()
//                .antMatchers("/authentication/form", "/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable();

        http    .userDetailsService(userDetailsService)
                // 配置登陆页/login并允许访问
                .formLogin().permitAll()
                // 登出页
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
                // 其余所有请求全部需要鉴权认证
                .and().authorizeRequests().anyRequest().authenticated()
                // 由于使用的是JWT，我们这里不需要csrf
                .and().csrf().disable();
    }
}
