//package com.wuzz.demo;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @description: 类功能描述
// * @author: wuzhenzhao
// * @time 2020/5/6 12:30
// * @since 1.0
// **/
//@RestController
//@RefreshScope
//public class TestController {
//
//    @Value("${foo.bar}")
//    String fooBar;
//
//    @GetMapping("/foo")
//    public String getFooBar() {
//        return fooBar;
//    }
//
//    @GetMapping("/hello")
//    public String home() {
//        return "hi ,i am from Consul:";
//    }
//}
