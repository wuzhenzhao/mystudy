package com.wuzz.demo.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/14 16:41
 * @since 1.0
 **/
@Configuration
public class SentinelAopConfiguration {

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
