package com.wuzz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/10
 * Time: 15:10
 * Description 描述:
 */
@RestController
public class RibbonController {

    //  private static final String REST_URL_PREFIX="http://localhost:8001"; 单机版
    //集群的时候  需要配置该服务在eureka里注册的名字
    private static final String REST_URL_PREFIX="http://cloud-provider";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value ="/hello")
    public String get() {
        return restTemplate.getForObject(REST_URL_PREFIX+"/hello", String.class);
    }

    //消费端可以调用服务发现
    @RequestMapping(value ="/discovery")
    public Object discovery() {

        return restTemplate.getForObject(REST_URL_PREFIX+"/discovery", Object.class);
    }
}
