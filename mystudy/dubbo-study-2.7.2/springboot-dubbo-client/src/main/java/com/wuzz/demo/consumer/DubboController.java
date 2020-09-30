package com.wuzz.demo.consumer;

import com.wuzz.demo.api.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * create-date: 2019/7/21-20:42
 */
@RestController
public class DubboController {
    //Dubbo提供的注解
    @DubboReference(loadbalance = "roundrobin",
              timeout = 9000, cluster = "failfast",
              mock = "com.wuzz.demo.mock.HelloServiceMock", check = false)
    HelloService helloService;

    @GetMapping("/sayhello")
    public String sayHello() throws Exception {
        return helloService.sayHello(); //我调用这个服务可能失败，如果失败了，我要怎么处理
    }

    // dubbo 泛化调用
    @DubboReference(interfaceName = "com.wuzz.demo.api.HelloService",generic = true,check = false)
    GenericService genericService;

    @GetMapping("/demo")
    public String demo(){
        return genericService.$invoke("sayHello",new String[0],null).toString();
    }


}
