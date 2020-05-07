package com.wuzz.demo.web;

import com.wuzz.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/7 10:39
 * @since 1.0
 **/
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public void test(){
        userService.test();
    }
}
