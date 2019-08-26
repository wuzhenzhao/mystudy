package com.wuzz.demo;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/8/22
 * Time: 21:28
 * Description 描述:
 */
public class ConsumerDemo extends Thread{

    private final KafkaConsumer kafkaConsumer;

    public ConsumerDemo(String topic) {
        Properties properties=new Properties();
        // 连接的 kafka 集群地址
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "192.168.1.101:9092,192.168.1.102:9092,192.168.1.103:9092");
        // 消费者分组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"KafkaConsumerDemo");
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG,"KafkaConsumerDemo1");
        //确认自动提交
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
        // 自动提交间隔
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
        // 序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        //对于不同的groupid保证能消费到之前的消息，重置offset
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        kafkaConsumer=new KafkaConsumer(properties);
        //订阅
        kafkaConsumer.subscribe(Collections.singletonList(topic));
    }

    @Override
    public void run() {
        while(true){
            ConsumerRecords<Integer,String> consumerRecords=kafkaConsumer.poll(Duration.ofSeconds(1));
            consumerRecords.forEach(record->{
                //null->gp kafka practice msg:0->63
                System.out.println(record.key()+"->"+record.value()+"->"+record.offset());
            });
        }
    }

    public static void main(String[] args) {
        new ConsumerDemo("testCopyTopic").start();
    }
}
