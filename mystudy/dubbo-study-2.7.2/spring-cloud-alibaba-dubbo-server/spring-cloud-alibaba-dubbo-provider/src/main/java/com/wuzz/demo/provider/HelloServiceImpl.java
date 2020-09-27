package com.wuzz.demo.provider;

import com.wuzz.demo.api.HelloService;
import org.apache.dubbo.config.annotation.Service;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/8/16
 * Time: 14:19
 * Description 描述:
 */
@Service(loadbalance = "random",timeout = 50000,cluster = "failsafe")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() throws Exception {
        return "Hello Dubbo";
    }
}
