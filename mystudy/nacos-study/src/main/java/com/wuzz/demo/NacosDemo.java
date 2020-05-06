package com.wuzz.demo;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @description: Nacos 基本操作API
 * @author: wuzhenzhao
 * @time 2020/5/6 15:39
 * @since 1.0
 **/
public class NacosDemo {
    public static void main(String[] args) {
        //连接到目标服务的地址
        //指定dataid、 groupid
        String serverAddr = "localhost:8848";
        String dataId = "example";
        String groupId = "DEFAULT_GROUP";
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        try {
            //ConfigService-> NacosConfigService
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, groupId, 3000);
            System.out.println(content);
            //监听
            configService.addListener(dataId, groupId, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }
                //具备监听功能，当配置修改后回通知到这里
                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("configInfo:" + configInfo);
                }
            });
            System.in.read();
        } catch (NacosException | IOException e) {
            e.printStackTrace();
        }

    }
}
