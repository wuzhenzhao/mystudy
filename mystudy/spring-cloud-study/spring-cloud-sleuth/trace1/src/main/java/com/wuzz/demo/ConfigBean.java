package com.wuzz.demo;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/10
 * Time: 15:06
 * Description 描述:
 */
@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced // ribbon是客户端 的负载均衡工具
    //默认算法是轮询算法 核心组件IRule
    public RestTemplate getRestTemplate() {

        return new RestTemplate();
    }

//    @Bean
//    public IRule ribbonRule() {
//        //随机负载
//        return new RoundRobinRule();
//    }
}
