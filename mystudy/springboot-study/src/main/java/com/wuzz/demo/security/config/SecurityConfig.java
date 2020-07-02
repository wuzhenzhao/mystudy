/**
 *
 */
package com.wuzz.demo.security.config;


import com.wuzz.demo.security.config.session.WuzzExpiredSessionStrategy;
import com.wuzz.demo.security.config.session.WuzzInvalidSessionStrategy;
import com.wuzz.demo.security.config.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@Configuration
@EnableWebSecurity// 开启Security
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启Spring方法级安全
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Secutiry 处理链
//	SecurityContextPersistenceFilter
//	--> UsernamePasswordAuthenticationFilter
//	--> BasicAuthenticationFilter
//	--> ExceptionTranslationFilter
//	--> FilterSecurityInterceptor
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;

    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SpringSocialConfigurer wuzzSpringSocialConfigurer;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    //密码加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy(){
        return new WuzzInvalidSessionStrategy();
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
        return new WuzzExpiredSessionStrategy();
    }

    // 自定义认证配置
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭Security功能
//        http.csrf().disable()
//                .authorizeRequests()
//                .anyRequest().permitAll()
//                .and().logout().permitAll();

        http.apply(wuzzSpringSocialConfigurer)
                .and()
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)

                .formLogin().loginPage("http://localhost:8080/#/login")//自定义登陆地址
                    .loginProcessingUrl("/authentication/form") //登录处理地址
                    .successHandler(myAuthenticationSuccessHandler) // 登陆成功处理器
                    .failureHandler(myAuthenctiationFailureHandler) // 登陆失败处理器
                    .permitAll()
                    .and()
                .sessionManagement()
//                    .invalidSessionUrl("http://localhost:8080/#/login")
                    .invalidSessionStrategy(invalidSessionStrategy)//session无效处理策略
                    .maximumSessions(1) //允许最大的session
//                    .maxSessionsPreventsLogin(true) //只允许一个地点登录，再次登陆报错
                    .expiredSessionStrategy(sessionInformationExpiredStrategy) //session过期处理策略，被顶号了
                    .and()
                    .and()
                .rememberMe()//实现记住我功能 RememberMeAuthenticationFilter
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(3600)
                    .userDetailsService(myUserDetailService)//设置userDetailsService，处理用户信息
                    .and()
                .authorizeRequests()
                .antMatchers("/wuzz/test4","/code/*").permitAll() //不需要保护的资源，可以多个
                .antMatchers("/wuzz/**").authenticated()// 需要认证得资源，可以多个
                .and()
        ;
        http.headers().cacheControl(); //禁用缓存
        http.csrf().disable(); //禁用csrf校验
    }

    //忽略的uri
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers( "/code/**","/api/**", "/resources/**", "/static/**", "/public/**", "/webui/**", "/h2-console/**"
						, "/configuration/**",  "/swagger-resources/**", "/api-docs", "/api-docs/**", "/v2/api-docs/**"
						,  "/**/*.css", "/**/*.js","/**/*.ftl", "/**/*.png ", "/**/*.jpg", "/**/*.gif ", "/**/*.svg", "/**/*.ico", "/**/*.ttf", "/**/*.woff");
	}

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //启动的时候是否创建该表，这个表格是保存用户登录信息的
//		tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}
