package com.wuzz.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @description: es 服务相关配置
 * @author: wuzhenzhao
 * @time 2020/5/15 20:05
 * @since 1.0
 **/
@Component
@PropertySource("classpath:es-config.properties")
@Data
public class EsConfig {
    //集群名称
    @Value("${es.cluster.name}")
    private String clusterName;

    // master ip
    @Value("${es.host.ip}")
    private String ip;

    //master port
    @Value("${es.host.port}")
    private int port;
}
