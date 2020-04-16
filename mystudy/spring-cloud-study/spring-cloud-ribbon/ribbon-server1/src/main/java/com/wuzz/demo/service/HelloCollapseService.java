package com.wuzz.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/4/16 11:14
 * @since 1.0
 **/
@Service
public class HelloCollapseService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String REST_URL_PREFIX = "http://cloud-provider";

    //同步
    public String hello(String id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/hello/{1}", String.class, id);
    }

    //同步
    public List<String> hi(List<String> ids) {
        String[] forObject = restTemplate.getForObject(REST_URL_PREFIX + "/hi?ids={1}", String[].class, StringUtils.join(ids, ","));
        return Arrays.asList(forObject);
    }

    @HystrixCollapser(batchMethod = "findAll",
            collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "100")})
    public Future<String> find(String id) {
        throw new RuntimeException("This method body should not be executed");
    }

    @HystrixCommand(fallbackMethod = "annotationBatchHelloBack")
    public List<String> findAll(List<String> ids) {
        System.out.println("Annotation---------" + ids + "Thread.currentThread().getName():" + Thread.currentThread().getName());
        String[] users = restTemplate.getForObject(REST_URL_PREFIX + "/hi?ids={1}", String[].class, StringUtils.join(ids, ","));
        return Arrays.asList(users);
    }

    public List<String> annotationBatchHelloBack(List<Long> ids) {
        return Arrays.asList("annotationBatchHelloBack Hystrix" +ids);
    }
}
