//package com.wuzz.demo.customer;
//
///**
// * @description:
// * @author: Wuzhenzhao
// * @time 2020/4/22 11:18
// * @since 1.0
// **/
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.cloud.stream.messaging.Sink;
//
//@EnableBinding(value = {SinkSender.class})
//public class SinkReceiver {
//
//    private static Logger logger = LoggerFactory.getLogger(SinkReceiver.class);
//
//    @StreamListener(Sink.INPUT)
//    public void receive(String payload) {
//        logger.info("Received Message: " + payload);
//    }
//}
//
