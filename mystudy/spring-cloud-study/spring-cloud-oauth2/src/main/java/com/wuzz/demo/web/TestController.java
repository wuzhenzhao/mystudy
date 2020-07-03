package com.wuzz.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/11
 * Time: 14:29
 * Description 描述:
 */
@RestController
@RequestMapping("/wuzz")
public class TestController {

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public String test3() {
        return "Hello Oauth2";
    }

}
