package com.wuzz.demo.service;

import com.wuzz.demo.config.HystrixClientService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/10
 * Time: 16:18
 * Description 描述:
 */
@FeignClient(value ="cloud-provider", fallbackFactory = HystrixClientService.class)
public interface ClientService {

    @RequestMapping(value ="/hello",method= RequestMethod.GET)
    String hello() ;
}
