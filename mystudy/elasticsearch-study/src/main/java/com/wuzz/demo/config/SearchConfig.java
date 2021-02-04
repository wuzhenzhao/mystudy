package com.wuzz.demo.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/15 20:05
 * @since 1.0
 **/
@Configuration
public class SearchConfig {

    @Autowired
    EsConfig esConfig;

    @Bean
    public TransportClient client() throws UnknownHostException {


        TransportAddress node = new TransportAddress(
                InetAddress.getByName(esConfig.getIp()),
                esConfig.getPort()
        );

        Settings settings = Settings.builder()
                .put("cluster.name", esConfig.getClusterName())
                .build();

        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);
        return client;
    }
}
