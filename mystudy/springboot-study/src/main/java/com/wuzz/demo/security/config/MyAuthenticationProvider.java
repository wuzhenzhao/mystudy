package com.wuzz.demo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/6/19 19:43
 * @since 1.0
 **/
@Configuration("myAuthenticationProvider")
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userName = authentication.getName();// 这个获取表单输入中的用户名
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = myUserDetailService.loadUserByUsername(userName);
        String encodePassword = passwordEncoder.encode(password);
        if (!passwordEncoder.matches(password,encodePassword)) {
            throw new UsernameNotFoundException("用户名或者密码不正确");
        }

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        return new UsernamePasswordAuthenticationToken(userDetails, encodePassword, authorities);

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
