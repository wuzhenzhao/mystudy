package com.wuzz.demo.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/6/15 15:14
 * @since 1.0
 **/
@Configuration
public class CustomerWebIntercepterConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomerIntercepter())
                // 拦截路劲
                .addPathPatterns("/wuzz/**")
                // 排除路径
                .excludePathPatterns("/wuzz/pus");
    }
}
