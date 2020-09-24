package com.wuzz.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/10
 * Time: 16:18
 * Description 描述:
 */
@FeignClient(value = "cloud-provider", fallbackFactory = HystrixClientService.class)
public interface ClientService {
    //如果feign代理的是get请求，必须用@RequestMapping 不能用@GetMapping
    // 每个参数必须带上@RequestParam，否则会报post not support！
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String hello(@RequestParam("id") String id) throws InterruptedException;
}
