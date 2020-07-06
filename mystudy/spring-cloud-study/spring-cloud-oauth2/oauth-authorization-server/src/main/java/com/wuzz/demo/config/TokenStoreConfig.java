package com.wuzz.demo.config;//package com.wuzz.demo.oauth.config;
//
//import com.wuzz.demo.oauth.jwt.WuzzJwtTokenEnhancer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Conditional;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
///**
// * @description: 类功能描述
// * @author: wuzhenzhao
// * @time 2020/7/3 19:46
// * @since 1.0
// **/
//@Configuration
//public class TokenStoreConfig {
//
//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
//
//    @Bean
//    @ConditionalOnProperty(prefix = "wuzz",name="storeType",havingValue = "redis")
//    public TokenStore redisTokenStore(){
//        return  new RedisTokenStore(redisConnectionFactory);
//    }
//    @Configuration
//    @ConditionalOnProperty(prefix = "wuzz",name="storeType",havingValue = "jwt",matchIfMissing = true)
//    public  static  class JwtTokenConfig{
//        //自包含、可拓展、密签
//        //https://www.jsonwebtoken.io/  解码
//        //{
//        // "exp": 1593785308,
//        // "user_name": "admin",
//        // "authorities": [
//        //  "admin",
//        //  "ROLE_USER"
//        // ],
//        // "jti": "e2e5e811-b235-49b8-8678-5bf22e265415",
//        // "client_id": "wuzzClientId",
//        // "scope": [
//        //  "all"
//        // ]
//        //}
//        @Bean
//        public TokenStore jwtTokenStore(){
//            return  new JwtTokenStore(jwtAccessTokenConverter());
//        }
//
//        @Bean
//        public JwtAccessTokenConverter jwtAccessTokenConverter() {
//            JwtAccessTokenConverter accessTokenConverter =new JwtAccessTokenConverter();
//            accessTokenConverter.setSigningKey("wuzz");
//            return accessTokenConverter;
//        }
//
//        @Bean
//        @ConditionalOnMissingBean(name="jwtTokenEnhancer")
//        public TokenEnhancer jwtTokenEnhancer(){
//            return new WuzzJwtTokenEnhancer();
//        }
//    }
//}
