//package com.wuzz.demo.integration;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.Output;
//import org.springframework.cloud.stream.messaging.Sink;
//import org.springframework.context.annotation.Bean;
//import org.springframework.integration.annotation.InboundChannelAdapter;
//import org.springframework.integration.annotation.Poller;
//import org.springframework.integration.annotation.Transformer;
//import org.springframework.integration.core.MessageSource;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.MessageHeaders;
//import org.springframework.messaging.support.GenericMessage;
//import org.springframework.messaging.support.MessageBuilder;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @description:
// * @author: Wuzhenzhao@hikvision.com.cn
// * @time 2020/4/22 15:29
// * @since 1.0
// **/
//@EnableBinding(value = {IntegrationSinkSender.SinkOutput.class})
//public class IntegrationSinkSender {
//    private static Logger logger = LoggerFactory.getLogger(IntegrationSinkSender.class);
//    //消息分区测试
////    @Bean
////    @InboundChannelAdapter(value = SinkOutput.OUTPUT, poller = @Poller(fixedDelay = "2000"))
////    public MessageSource<CustomerMessage> timerMessageSource() {
////        CustomerMessage customerMessage = new CustomerMessage();
////        customerMessage.setId("111");
////        customerMessage.setBody("hello");
////        Message<CustomerMessage> partitionKey = MessageBuilder.withPayload(customerMessage).setHeader("partitionKey", 1).build();
////        return () -> partitionKey;
////
////    }
//
//    @Bean
//    @InboundChannelAdapter(value = SinkOutput.OUTPUT, poller = @Poller(fixedDelay = "2000"))
//    public MessageSource<Date> timerMessageSource() {
//        return () -> new GenericMessage<>(new Date());
//
//    }
//
////    @Transformer(inputChannel = Sink.INPUT, outputChannel = SinkOutput.OUTPUT)
////    public Object transform(Date message) {
////        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(message) + " wuzz";
////    }
//
//
//    public interface SinkOutput {
//        String OUTPUT = Sink.INPUT;
//
//        @Output(SinkOutput.OUTPUT)
//        MessageChannel output();
//    }
//}
