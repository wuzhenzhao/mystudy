package com.wuzz.demo.config;


import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/6/12 19:35
 * @since 1.0
 **/
@Configuration
public class ShiroConfig {
    /**
     * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
     * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * HashedCredentialsMatcher，这个类是为了对密码进行编码的，
     * 防止密码在数据库里明码保存，当然在登陆认证的时候，
     * 这个类也负责对form里输入的密码进行编码。
     */
//    @Bean(name = "hashedCredentialsMatcher")
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//        credentialsMatcher.setHashAlgorithmName("MD5");
//        credentialsMatcher.setHashIterations(1024);
//        credentialsMatcher.setStoredCredentialsHexEncoded(true);
//        return credentialsMatcher;
//    }

//    public static void main(String[] args) {
//        String hashAlgorithmName = "MD5";
//        String credentials = "123456";
//        int hashIterations = 1024;
//        Object obj = new SimpleHash(hashAlgorithmName, credentials, null, hashIterations);
//        System.out.println(obj);
//    }

    /**
     * ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，
     * 负责用户的认证和权限的处理，可以参考JdbcRealm的实现。
     */
    @Bean(name = "shiroRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public CustomRealm shiroRealm() {
        CustomRealm realm = new CustomRealm();//这个类需要自己编写 下面会贴出其实现
//        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    /**
     * SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。
     * //
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("shiroRealm") CustomRealm shiroRealm, @Qualifier("defaultWebSessionManager") SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    /**
     * ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。
     * 它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filters = new LinkedHashMap<>();
//        filters.put("user",new ShiroUserFilter());
//        LogoutFilter logoutFilter = new LogoutFilter();
//        logoutFilter.setRedirectUrl("/api/1.0/loginout");
//        filters.put("logout",null);
//        shiroFilterFactoryBean.setFilters(filters);
        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<String, String>();
//        filterChainDefinitionManager.put("/logout", "logout");//登出URL
        filterChainDefinitionManager.put("/login", "anon");//登陆URL
        filterChainDefinitionManager.put("/nologin", "anon");//未登录跳转的URL
        filterChainDefinitionManager.put("/test", "anon");
//      filterChainDefinitionManager.put("/user/edit/**", "authc,perms[user:edit]");// 这里为了测试，固定写死的值，也可以从数据库或其他配置中读取，此处是用权限控制
//        filterChainDefinitionManager.put("/**", "authc");
        filterChainDefinitionManager.put("/**", "user"); // 需要登录
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);
        shiroFilterFactoryBean.setLoginUrl("/login"); // 登录地址
        shiroFilterFactoryBean.setFilters(filters);
//        shiroFilterFactoryBean.setUnauthorizedUrl("/api/1.0/unauth");
        return shiroFilterFactoryBean;
    }

    /**
     * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aASA = new AuthorizationAttributeSourceAdvisor();
        aASA.setSecurityManager(securityManager);
        return aASA;
    }

    @Bean("defaultWebSessionManager")
    public DefaultWebSessionManager configWebSessionManager(@Qualifier("redisSessionDao") RedisSessionDao sessionDao) {
        MySessionManager manager = new MySessionManager();
        manager.setSessionDAO(sessionDao);// 设置SessionDao
        manager.setDeleteInvalidSessions(true);// 删除过期的session
        manager.setSessionValidationSchedulerEnabled(false);// 是否定时检查session
        return manager;
    }
}
