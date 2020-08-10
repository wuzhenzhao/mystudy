package com.wuzz.demo;

import com.wuzz.demo.entity.SysUser;
import com.wuzz.demo.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDSTests {

    @Autowired
    private SysUserService userService;

    @Test
    public void contextLoads() {
        SysUser user = userService.getById(1);
        log.info(user.toString());
    }

    @Test
    public void test() {
        // localhost:3306/ds0
        SysUser user = userService.findUserByFirstDb(1);
        log.info("第一个数据库 : [{}]", user.toString());
        // localhost:3306/ds1
        SysUser user2 = userService.findUserBySecondDb(2);
        log.info("第二个数据库 : [{}]", user2.toString());
    }
}
