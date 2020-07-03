//package com.wuzz.demo.security.config.social;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.social.connect.Connection;
//import org.springframework.social.connect.ConnectionData;
//import org.springframework.social.connect.ConnectionFactoryLocator;
//import org.springframework.social.connect.UsersConnectionRepository;
//import org.springframework.social.connect.web.ProviderSignInAttempt;
//import org.springframework.web.context.request.RequestAttributes;
//
//import javax.servlet.annotation.WebServlet;
//
///**
// * @description: 类功能描述
// * @author: wuzhenzhao
// * @time 2020/7/3 18:44
// * @since 1.0  用域APP第三方注册相关
// **/
//public class AppSignUpUtils {
//
////    @Autowired
////    private RedisTemplate redisTemplate;
//
//    @Autowired
//    private UsersConnectionRepository usersConnectionRepository;
//
//    @Autowired
//    private ConnectionFactoryLocator connectionFactoryLocator;
//
//    public  void saveConnectionData(WebServlet request, ConnectionData connectionData){
//        // redisTemplate 存储
//    }
//
//    public void doPostSignUp(WebServlet request,String userId) {
//        String key = getKey(request);
//        ConnectionData connectionData =new ConnectionData();  //从redis 获取
//        Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId()).createConnection(connectionData);
//        usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);
//    }
//
//    private String getKey(WebServlet request){
//        //从 request 获取到存入redis的key
//        return null;
//    }
//}
