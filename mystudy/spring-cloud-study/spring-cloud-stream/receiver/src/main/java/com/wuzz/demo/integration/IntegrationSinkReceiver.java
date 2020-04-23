//package com.wuzz.demo.integration;
//
//import com.alibaba.fastjson.JSON;
//import com.wuzz.demo.CustomerMessage;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.cloud.stream.messaging.Sink;
//import org.springframework.cloud.stream.messaging.Source;
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.messaging.handler.annotation.Headers;
//import org.springframework.messaging.handler.annotation.Payload;
//
//import java.util.Date;
//import java.util.Map;
//
///**
// * @description:跟IntegrationSinkSender需要放在两个应用里。
// * @author: Wuzhenzhao@hikvision.com.cn
// * @time 2020/4/22 15:27
// * @since 1.0
// **/
//@EnableBinding(value = {Sink.class})
//public class IntegrationSinkReceiver {
//    private static Logger logger = LoggerFactory.getLogger(IntegrationSinkReceiver.class);
//
//    @ServiceActivator(inputChannel=Sink.INPUT)
//    public void receive(@Payload Date payload, @Headers Map headers) {
//        logger.info(headers.get("contentType").toString());
//        logger.info("Received from {} channel Date: {}", Sink.INPUT, payload);
//    }
//
////    @ServiceActivator(inputChannel = Sink.INPUT)
////    public void receive(String payload) {
////        logger.info("Received from {} channel Date: {}", Sink.INPUT + Source.OUTPUT, payload);
////    }
//     //传输对象
////    @StreamListener(Sink.INPUT)
////    public void receive(CustomerMessage message,@Headers Map headers) {
////        logger.info(headers.get("partitionKey").toString());
////        logger.info("Received from {} channel Date: {}", Sink.INPUT + Source.OUTPUT, JSON.toJSONString(message));
////    }
//}
