package com.wuzz.demo.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/7/3 10:03
 * @since 1.0
 **/
@Configuration
@Order(1)
public class OauthConfig {

        //密码加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
