package com.wuzz.demo.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/7/2 19:43
 * @since 1.0
 **/
@Configuration
@EnableResourceServer
public class WuzzResourceServerConfig {
    //http://localhost:8889/oauth/authorize?client_id=wuzzClientId&response_type=code&redirect_uri=http://www.wuzz.demo&scope=all
}
