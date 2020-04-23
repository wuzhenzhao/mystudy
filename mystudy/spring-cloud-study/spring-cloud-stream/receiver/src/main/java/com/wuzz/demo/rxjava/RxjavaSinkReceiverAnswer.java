package com.wuzz.demo.rxjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.rxjava.EnableRxJavaProcessor;
import org.springframework.cloud.stream.annotation.rxjava.RxJavaProcessor;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Transformer;

import java.text.SimpleDateFormat;

/**
 * @description:跟IntegrationSinkSender需要放在两个应用里。
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/4/22 15:27
 * @since 1.0
 **/
@EnableRxJavaProcessor
public class RxjavaSinkReceiverAnswer {

    private static Logger logger = LoggerFactory.getLogger(RxjavaSinkReceiverAnswer.class);


    @Bean
    public RxJavaProcessor<String, String> receive() {
        //data 就是接收方的消息
        return inputStream -> inputStream.map(data -> {
            logger.info("Received: " + data);
            return data;
            //返回发送端的消息
        }).buffer(5).map(data -> String.valueOf("From Input Channel Return - " + data));

    }

//    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.INPUT)
//    public Object transform(byte[] message) {
//        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(message) + " Byte[]";
//    }
}

