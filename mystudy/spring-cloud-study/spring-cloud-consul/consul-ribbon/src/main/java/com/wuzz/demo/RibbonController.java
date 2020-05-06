package com.wuzz.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

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
    private static final String REST_URL_PREFIX = "http://consul-server-hello";

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping(value = "/consulHello")
    public String find() throws ExecutionException, InterruptedException {

        return restTemplate.getForObject(REST_URL_PREFIX + "/hello", String.class);
    }

    @RequestMapping(value = "/foo")
    public String foo() throws ExecutionException, InterruptedException {

        return restTemplate.getForObject(REST_URL_PREFIX + "/foo", String.class);
    }


}
