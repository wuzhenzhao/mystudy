package com.wuzz.demo;

import com.wuzz.demo.event.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/6/27
 * Time: 14:57
 * Description 描述:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringStudyApp.class)
public class SpringStudyTest {

    @Autowired
    OrderService orderService;

    @Test
    public void contextLoads() {
        orderService.save();
    }

}
