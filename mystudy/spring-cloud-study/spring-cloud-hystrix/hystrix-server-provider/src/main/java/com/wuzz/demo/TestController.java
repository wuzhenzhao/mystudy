package com.wuzz.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/10
 * Time: 14:43
 * Description 描述:
 */
@RestController
public class TestController {

    @Autowired//服务发现
    private DiscoveryClient client;

    @GetMapping("/hello")
    @HystrixCommand(fallbackMethod = "processHystrix_Get")//熔断机制
    public String helloEureka(Long id) {
        if (id == null) {
            throw new RuntimeException();
        }
        return "Hello Eureka Provider1";
    }

    public String processHystrix_Get(Long id) {
        return "hello Hystrix";
    }

    /**
     * 服务发现
     *
     * @return
     */
    @RequestMapping(value = "/discovery", method = RequestMethod.GET)
    public Object discovery() {
        List<String> list = client.getServices();
        List<ServiceInstance> instances = client.getInstances("");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getHost());
        }
        return this.client;
    }
}
