package com.wuzz.demo.aop.web;

import com.wuzz.demo.aop.TransationTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/5/20
 * Time: 10:39
 * Description 描述:
 */
@RestController
public class TestController {

    @Autowired
    private TransationTest transationTest;

    private final static Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/get")
    public String getName(String name) {
        log.info("******进入controller方法*************");
        return "Hello " + name;
    }

    @GetMapping("/isTransation")
    public void isTransation(String name) {
        transationTest.isTransation();
    }

    @GetMapping("/noTransation")
    public void noTransation(String name) {
        transationTest.noTransation();
    }
}
