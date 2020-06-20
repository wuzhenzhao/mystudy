/**
 *
 */
package com.wuzz.demo.security.config;


import com.wuzz.demo.security.config.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author zhailiang
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and().logout().permitAll();

//        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeRequests()
//                .antMatchers("/wuzz/test4","/code/*").permitAll() //不需要保护的资源
//                .antMatchers("/wuzz/**").authenticated()// 需要认证得资源
//                .and().formLogin().loginPage("http://localhost:8080/#/login")
//                .loginProcessingUrl("/authentication/form")
//                .successHandler(myAuthenticationSuccessHandler) // 登陆成功处理器
//                .failureHandler(myAuthenctiationFailureHandler) // 登陆失败处理器
//                .permitAll()
//                .and()
//                .userDetailsService(myUserDetailService)//设置userDetailsService
//        ;
//        http.headers().cacheControl(); //禁用缓存
//        http.csrf().disable();
    }

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring()
//				.antMatchers( "/api/**", "/resources/**", "/static/**", "/public/**", "/webui/**", "/h2-console/**"
//						, "/configuration/**",  "/swagger-resources/**", "/api-docs", "/api-docs/**", "/v2/api-docs/**"
//						,  "/**/*.css", "/**/*.js","/**/*.ftl", "/**/*.png ", "/**/*.jpg", "/**/*.gif ", "/**/*.svg", "/**/*.ico", "/**/*.ttf", "/**/*.woff");
//	}


}
