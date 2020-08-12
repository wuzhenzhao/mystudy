package com.wuzz.demo;

import com.wuzz.demo.dao.UserDao;
import com.wuzz.demo.entity.User;
import com.wuzz.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Test
    public void insert1() throws Exception {
        User user = new User();
         user.setUserId(2526);
        user.setName("test");
        user.setAge(22);
        this.userDao.addOne(user);
        log.info("插入用户id：{}", user.getUserId());
    }
}

