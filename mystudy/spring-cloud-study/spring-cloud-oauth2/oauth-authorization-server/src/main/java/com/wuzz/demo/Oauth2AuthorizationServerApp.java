package com.wuzz.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@SpringBootApplication
public class Oauth2AuthorizationServerApp {

    /**
     * 在springboot-study模块中。对于springsecurity提供的登录用户名密码登录、短信登陆、第三方社交登录。
     * 是采用的服务器 session 的方式，那么在现在APP的场景下 没有这个session了，那么针对这集中登陆方式怎么实现呢？
     * 1.用户名密码登录：已经在本模块实现，登陆的时候带上  clientId + client-secret
     * 2.短信登陆原来验证码是放到session里，这里可以采用放到一个共享存储里，如redis。
     * 3.社交登录的时候，用户是在APP去访问第三方应用  比如 QQ  微信。获取到openId后 来访问后台显然是行不通的
     * 所以后台需要提供一个根据openId登录的方式，具体方式参照springboot-study模块 短信验证码登录的方式。
     * 3.1 对于第三方的注册逻辑也需要修改 ，主要是修改ProviderSignInUtils
     */
    private final static Logger log = LoggerFactory.getLogger(Oauth2AuthorizationServerApp.class);

    public static void main(String[] args) {
        SpringApplication.run(Oauth2AuthorizationServerApp.class, args);
        log.info("服务启动成功 ");

    }
}
