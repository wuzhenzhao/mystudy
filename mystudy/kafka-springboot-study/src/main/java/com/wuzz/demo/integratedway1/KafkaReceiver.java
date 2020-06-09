package com.wuzz.demo.integratedway1;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.apache.kafka.common.requests.DeleteAclsResponse.log;

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

    @KafkaListener(containerFactory = "kafkaBatchListener6", topics = {"testCopyTopic"}, groupId = "testGroup")
    public void listen(ConsumerRecord<?, ?> record, Acknowledgment ack) {
        try {
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            if (kafkaMessage.isPresent()) {

                Object message = kafkaMessage.get();
                System.out.println("----------------- record =" + record);
                System.out.println("------------------ message =" + message);
            }
        } catch (Exception e) {
            log.error("Kafka监听异常" + e.getMessage(), e);
        } finally {
            ack.acknowledge();//手动提交偏移量
        }
    }

//    @KafkaListener(containerFactory = "kafkaBatchListener6", topics = {"testCopyTopic"},groupId = "testGroup")
//    public void batchListener(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
//
//        List<User> userList = new ArrayList<>();
//        try {
//            records.forEach(record -> {
//                System.out.println("----------------- record =" + record);
//                System.out.println("------------------ message =" + record);
//            });
//        } catch (Exception e) {
//            log.error("Kafka监听异常" + e.getMessage(), e);
//        } finally {
//            ack.acknowledge();//手动提交偏移量
//        }
//
//    }
}
