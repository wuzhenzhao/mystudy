package com.wuzz.demo.config;

import com.wuzz.demo.moudle.dao.UserDao;
import com.wuzz.demo.moudle.entity.Role;
import com.wuzz.demo.moudle.entity.User;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录用户名:" + username);
        User user = userDao.selectUserByUsername(username);
        List<String> roleCodes = user.getRoles().stream().map(Role::getRoleCode).collect(Collectors.toList());
        String authorities = StringUtils.join(roleCodes, ",");
        // 根据用户名查找用户信息
        //根据查找到的用户信息判断用户是否被冻结
        String password = passwordEncoder.encode("123456");
        logger.info("数据库密码是:" + password);
        user.setPassword(password);
        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
        return user;
    }

}
