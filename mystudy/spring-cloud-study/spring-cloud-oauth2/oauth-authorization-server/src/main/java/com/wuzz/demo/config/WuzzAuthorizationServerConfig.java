package com.wuzz.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/7/2 19:43
 * @since 1.0
 **/
@Configuration
@EnableAuthorizationServer
public class WuzzAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    //http://localhost:8766/oauth/authorize?client_id=wuzzClientId&response_type=code&redirect_uri=http://www.baidu.com&scope=all

    // 自定义token存储类型
    @Autowired
    private TokenStore tokenStore;

    // jwt token
    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    //jwt token 附加信息
    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory().withClient("wuzzClientId")//客户端得ID,比如我们在QQ互联中心申请得。可以写多个。配置 循环
//                .secret(passwordEncoder().encode("wuzzSecret")) // 客户端密钥，需要进行加密
//                .accessTokenValiditySeconds(7200)// token 有效时常  0 永久有效
//                .authorizedGrantTypes("password", "implicit", "refresh_token", "authorization_code")// 支持得授权类型
//                .redirectUris("http://www.baidu.com")//回调地址
//                .scopes("all", "read", "write");//拥有的 scope  可选
        clients.withClientDetails(new JdbcClientDetailsService(dataSource));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.userDetailsService(userDetailsService()) // 用户信息得服务，一版是都数据库
                .authenticationManager(authenticationManager())// 认证管理器。
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore);
        if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(jwtTokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);
            tokenEnhancerChain.setTokenEnhancers(enhancers);

            endpoints.tokenEnhancer(tokenEnhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()//允许表单登录
                .checkTokenAccess("permitAll()"); //开启/oauth/check_token验证端口认证权限访问
    }

    @Bean // 注入认证管理器
    public AuthenticationManager authenticationManager() {
        AuthenticationManager authenticationManager = new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return daoAuthenticationProvider().authenticate(authentication);
            }
        };
        return authenticationManager;
    }

    @Bean//注入认证器
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean//注入 用户信息服务
    public UserDetailsService userDetailsService() {
        return new MyUserDetailService();
    }

    @Bean//注入密码加密
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
