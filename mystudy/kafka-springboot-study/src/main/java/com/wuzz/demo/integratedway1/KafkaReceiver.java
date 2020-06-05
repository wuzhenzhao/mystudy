package com.wuzz.demo.integratedway1;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/9
 * Time: 19:40
 * Description:
 * ClassPath:com.wuzz.demo.integratedway1.KafkaReceiver
 */
@Component
public class KafkaReceiver {
    private static Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

//    @KafkaListener(topics = {"wuzzTopic"})
//    public void listen(ConsumerRecord<?, ?> record) {
//        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
//        if (kafkaMessage.isPresent()) {
//
//            Object message = kafkaMessage.get();
//            System.out.println("----------------- record =" + record);
//            System.out.println("------------------ message =" + message);
//        }
//
//    }

    @KafkaListener(topics = "testCopyTopic", id = "consumer", containerFactory = "batchFactory")
    public void listen(List<ConsumerRecord<?, ?>> list) {
        List<String> messages = new ArrayList<>();
        for (ConsumerRecord<?, ?> record : list) {
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            // 获取消息
            kafkaMessage.ifPresent(o -> messages.add(o.toString()));
        }
    }
}
