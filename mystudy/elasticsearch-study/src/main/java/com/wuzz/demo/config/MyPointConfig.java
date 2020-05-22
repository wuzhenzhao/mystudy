package com.wuzz.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @description: 我自己所处的经纬度
 * @author: wuzhenzhao
 * @time 2020/5/15 20:10
 * @since 1.0
 **/
@Component
@PropertySource("classpath:my-point.properties")
@Data
public class MyPointConfig {
    @Value("${my.point.lon}")
    private double lon;

    @Value("${my.point.lat}")
    private double lat;
}
