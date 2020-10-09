package com.wuzz.demo.service;

import com.wuzz.demo.domain.User;
import com.wuzz.demo.mapper.mapper1.UserMapper1;
import com.wuzz.demo.mapper.mapper2.UserMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：wuzhenzhao
 * @date ：Created in 2019/1/24 14:39
 */
@Service
public class UserServiceImpl implements com.wuzz.demo.service.impl.UserService {

    @Autowired
    private UserMapper1 userMapper1;

    @Autowired
    private UserMapper2 userMapper2;

    @Override
    @Transactional(transactionManager = "xatx")
    public int insert(User user) {
        userMapper1.insert(user);
//        int k =1/0;
        userMapper2.insert(user);


        return 1;
    }
}
