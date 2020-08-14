package com.wuzz.demo.javaxapi.config;

import com.wuzz.demo.javaxapi.AppApiService;
import com.wuzz.demo.javaxapi.IAppApiService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

/**
 * @Description Description
 * @Author 一一哥Sun
 * @Date Created in 2020/3/28
 * @ApplicationPath("shop")资源根路径。
 */
@Configuration
@ApplicationPath("shop")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(AppApiService.class);
    }

}
