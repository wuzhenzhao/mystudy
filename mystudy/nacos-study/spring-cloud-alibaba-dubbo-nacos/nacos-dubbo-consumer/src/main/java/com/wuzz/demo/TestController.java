package com.wuzz.demo;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/9/8 16:19
 * @since 1.0
 **/
@RefreshScope
@RestController
public class TestController {

    @Reference
    EchoService echoService;

    @GetMapping("/call")
    public String call() {
        return echoService.echo("Wuzz");
    }

    @Value("${info:hello Nacos}")

    private String info;

    @GetMapping("/get")
    public String get() {
        return info;
    }
}
