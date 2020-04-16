package com.wuzz.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(String id) throws InterruptedException {
//        if (StringUtils.isEmpty(id)) {
//            throw new RuntimeException();
//        }
        // 测试fegin 超时重试代码开始
        List<ServiceInstance> instances = client.getInstances("feign-server");
        //测试超时
        int sleepTime = new Random().nextInt(3000);
        System.out.println("sleepTime:" + sleepTime);
        Thread.sleep(sleepTime);
        System.out.println("/hello, host:" + instances.get(0).getHost() +
                instances.get(0).getServiceId());
        // 测试fegin 超时重试代码结束
        return "Hello Eureka Provider"+ id;
    }

    @GetMapping("/hi")
    public List<String> hi(String ids) {
        String[] split = ids.split(",");
        ArrayList<String> objects = new ArrayList<String>();
        for (String s : split) {
            objects.add("hi! wuzz:ID: " + s);
        }
        return objects;
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
