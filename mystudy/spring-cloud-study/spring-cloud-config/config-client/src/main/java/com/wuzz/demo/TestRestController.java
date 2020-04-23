package com.wuzz.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/4
 * Time: 12:25
 * Description 描述:
 */
@RestController
@RefreshScope
public class TestRestController {

    @Value("${foo}")
    String foo;

    @RequestMapping(value = "/hello")
    public String hello(){
        return foo;
    }

    public static void main(String[] args) {

        int a = Math.abs("anonymous.8c5d85b0-6ded-4dba-8dc2-ebe2fcd38081".hashCode()) % 50;
        int b = Math.abs("anonymous.1d59a2da-cf76-4b43-99bb-49fab0645de7".hashCode()) % 50;
        System.out.println(a);
        System.out.println(b);
    }
}
