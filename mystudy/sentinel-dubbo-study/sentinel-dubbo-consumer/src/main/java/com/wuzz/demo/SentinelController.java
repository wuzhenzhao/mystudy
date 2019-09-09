package com.wuzz.demo;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/5/5
 * Time: 16:51
 * Description 描述:
 */
@RestController
public class SentinelController {

    @Reference(timeout = 3000,check = false)
    SentinelService sentinelService;//proxy$0

    @GetMapping("/say")
    public String sayHello(){
        RpcContext.getContext().setAttachment("dubboApplication","sentinel-web");
        return sentinelService.sayHello("test");
    }

    @GetMapping("/say2")
    public String sayHello2(){
        return sentinelService.sayHello("test2");
    }
}
