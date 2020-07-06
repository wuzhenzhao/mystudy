package com.wuzz.demo.oauth.jwt;//package com.wuzz.demo.oauth.jwt;
//
//import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @description: 类功能描述
// * @author: wuzhenzhao
// * @time 2020/7/3 20:14
// * @since 1.0
// **/
//public class WuzzJwtTokenEnhancer implements TokenEnhancer {
//
//    @Override
//    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
//
//        Map<String, Object> info = new HashMap<String, Object>();
//        info.put("company","alibaba");
//        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(info);
//        return oAuth2AccessToken;
//    }
//}
