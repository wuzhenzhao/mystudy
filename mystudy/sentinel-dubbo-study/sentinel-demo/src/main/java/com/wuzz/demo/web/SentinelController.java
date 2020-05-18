package com.wuzz.demo.web;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/14 16:42
 * @since 1.0
 **/
@RestController
public class SentinelController {
    //-Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-demo
    //针对方法级别的限流
    @SentinelResource(value = "sayHello", blockHandler = "exceptionHandler", fallback = "fallbackHandler")
    @GetMapping("/say")
    public String sayHello(String name) {
        System.out.println("hello world");
//        if (1 == 1) throw new RuntimeException("Hello");
        return "hello world";
    }

    // 该方法的参数除了 BlockException，包括参数、返回值，需要与原来的资源方法一致
    public String exceptionHandler(String name, BlockException ex) {
        return "blockHandler：" + name + ex.getMessage();
    }

    //用于在抛出异常的时候提供 fallback处理逻辑。
    public String fallbackHandler(String name) {
        return "fallbackHandler：" + name;
    }
}
