package com.wuzz.demo.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
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
    //http://localhost:8889/oauth/authorize?client_id=wuzzClientId&response_type=code&redirect_uri=http://www.wuzz.demo&scope=all


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 自定义token存储类型
    @Autowired
    private TokenStore tokenStore;

    // jwt token
    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    //jwt token 附加信息
    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired(required = false)
    private DataSource dataSource;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
        ;
        if (jwtAccessTokenConverter != null && jwtTokenEnhancer!=null) {
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancers=new ArrayList<>();
            enhancers.add(jwtTokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);
            tokenEnhancerChain.setTokenEnhancers(enhancers);

            endpoints.tokenEnhancer(tokenEnhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("wuzzClientId")//可以写多个。配置 循环
                .secret(passwordEncoder.encode("wuzzSecret"))
                .accessTokenValiditySeconds(7200)// token 有效时常  0 永久有效
                .authorizedGrantTypes("password", "implicit","refresh_token")
                .scopes("all", "read", "write");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }
}
