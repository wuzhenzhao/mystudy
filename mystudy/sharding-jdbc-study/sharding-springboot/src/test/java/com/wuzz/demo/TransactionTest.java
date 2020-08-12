package com.wuzz.demo;

import com.wuzz.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/8/10 19:59
 * @since 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TransactionTest {

    @Autowired
    private UserService userService;

    @Test
    public void transTest() {

        this.userService.testTransactional();
    }
}
