package com.wuzz.demo.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/4/22 15:29
 * @since 1.0
 **/
@EnableBinding(value = {Processor.class})
public class IntegrationSinkSenderAnswer {
    private static Logger logger = LoggerFactory.getLogger(IntegrationSinkSenderAnswer.class);

    @Bean
    @InboundChannelAdapter(value = Processor.OUTPUT, poller = @Poller(fixedDelay = "2000"))
    public MessageSource<String> timerMessageSource() {
        Map map =new HashMap();
        map.put("contentType","text/plain;charset=UTF-8");
        return () -> new GenericMessage<>(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "RxJava",new MessageHeaders(map));

    }

//    @Transformer(inputChannel = Processor.OUTPUT, outputChannel = Processor.OUTPUT)
//    public Object transform(Date message) {
//        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(message) + " Wuzz";
//    }

    // 监听input通道
    @StreamListener(Processor.INPUT)
    public void receiveFromInput(String s) {
        logger.info("SendToSender Received: " + s);

    }


}
