package com.wuzz.demo.consumer;

import com.wuzz.demo.api.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/7/21-20:42
 */
@RestController
public class DubboController {
    //Dubbo提供的注解
    @Reference(loadbalance = "roundrobin",
            timeout = 1, cluster = "failfast",
            mock = "com.wuzz.demo.mock.HelloServiceMock", check = false)
    HelloService helloService;

    @GetMapping("/sayhello")
    public String sayHello() throws Exception {
        return helloService.sayHello(); //我调用这个服务可能失败，如果失败了，我要怎么处理
    }


}
