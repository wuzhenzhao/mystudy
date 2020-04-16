package com.wuzz.demo.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/4/15 20:38
 * @since 1.0
 **/
public class HelloCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate;

    private HashMap map;

    public HelloCommand(RestTemplate restTemplate, HashMap paramMap) {
        super(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("helloCommand")).andCommandPropertiesDefaults(
                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000)));
        this.restTemplate = restTemplate;
        this.map = paramMap;
    }

    @Override
    protected String run() {
        return restTemplate.getForObject("http://cloud-provider/hello?id={id}", String.class, map);
    }

    @Override
    protected String getFallback() {
//        getFailedExecutionException()
        return "error-err";
    }
}
