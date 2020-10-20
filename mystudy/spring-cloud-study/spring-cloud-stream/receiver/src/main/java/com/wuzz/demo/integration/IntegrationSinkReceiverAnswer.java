//package com.wuzz.demo.integration;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.cloud.stream.messaging.Processor;
//import org.springframework.cloud.stream.messaging.Sink;
//import org.springframework.cloud.stream.messaging.Source;
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.integration.annotation.Transformer;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.scheduling.annotation.EnableScheduling;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @description:跟IntegrationSinkSender需要放在两个应用里。
// * @author: Wuzhenzhao
// * @time 2020/4/22 15:27
// * @since 1.0
// **/
//@EnableScheduling
//@EnableBinding(value = {Processor.class})
//public class IntegrationSinkReceiverAnswer {
//
//    private static Logger logger = LoggerFactory.getLogger(IntegrationSinkReceiverAnswer.class);
//
//
//    @ServiceActivator(inputChannel = Processor.INPUT,outputChannel = Processor.OUTPUT)
//    public Object receive(String payload) {
//        logger.info("Received from {} channel Date: {}", Processor.INPUT, payload);
//        return "From Input Channel Return - " + payload;
//    }
//
//    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.INPUT)
//    public Object transform(Date message) {
//        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(message);
//    }
//}
////@EnableBinding(value = {Processor.class})
////public class IntegrationSinkReceiverAnswer {
////
////    private static Logger logger = LoggerFactory.getLogger(IntegrationSinkReceiverAnswer.class);
////
////
////    @StreamListener(Processor.INPUT)
////    @SendTo(Processor.OUTPUT)
////    public Object receive(String payload) {
////        logger.info("Received from {} channel Date: {}", Processor.INPUT, payload);
////        return "From Input Channel Return - " + payload;
////    }
////
////    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.INPUT)
////    public Object transform(Date message) {
////        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(message);
////    }
////}
