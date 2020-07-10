package com.wuzz.demo;

import com.wuzz.demo.moudle.dao.UserDao;
import com.wuzz.demo.moudle.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/6/27
 * Time: 14:57
 * Description 描述:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Oauth2AuthorizationServerApp.class)
public class MybatisTest {

    @Autowired
    private UserDao userDao;


    @Test
    public void testSelectUserByUsername() {
        User wuzz = userDao.selectUserByUsername("Jone");
        System.out.println(wuzz);
    }


}
