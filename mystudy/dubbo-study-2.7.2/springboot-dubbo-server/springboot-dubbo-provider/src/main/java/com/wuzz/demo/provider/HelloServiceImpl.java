package com.wuzz.demo.provider;

import com.wuzz.demo.api.HelloService;
import org.apache.dubbo.config.annotation.Argument;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/8/16
 * Time: 14:19
 * Description 描述:
 */
@DubboService(loadbalance = "random", // 负载均衡
        timeout = 50000, //超时
        cluster = "failsafe", // 服务容错
        protocol = {"dubbo", "rest"}, //多协议支持
        registry = {"hangzhou", "wenzhou"}//多注册中心
)
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() throws Exception {
        return "Hello Dubbo";
    }
}
