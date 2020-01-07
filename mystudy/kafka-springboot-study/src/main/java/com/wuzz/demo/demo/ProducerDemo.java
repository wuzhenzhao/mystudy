package com.wuzz.demo.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/8/22
 * Time: 21:17
 * Description 描述:
 */
public class ProducerDemo extends Thread {

    private final KafkaProducer<String, String> producer;

    private final String topic;

    public ProducerDemo(String topic) {
        Properties properties = new Properties();
        // 连接的 kafka 集群地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "192.168.1.101:9092,192.168.1.102:9092,192.168.1.103:9092");
        // 客户端ID标识
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaProducerDemo");
        //确认记录，保证记录不丢失 总是设置成-1
        properties.put(ProducerConfig.ACKS_CONFIG, "-1");
        // 键序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.IntegerSerializer");
        //值序列化
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(properties);
        this.topic = topic;
    }

    @Override
    public void run() {
        int num=0;
        while(num<2000000) {
            try {

                // 方法 1: 使用 callback
//                producer.send(new ProducerRecord<String, String>("topic0", "message 2"), new Callback() {
//
//                    public void onCompletion(RecordMetadata metadata, Exception exception) {
//                        if(exception != null) {
//                            System.out.println("send message2 failed with " + exception.getMessage());
//                        } else {
//                            // offset 是消息在 partition 中的编号，可以根据 offset 检索消息
//                            System.out.println("message2 sent to " + metadata.topic() + ", partition " + metadata.partition() + ", offset " + metadata.offset());
//                        }
//
//                    }
//
//                });
                String msg="kafka practice msg:"+num;
                //get 会拿到发送的结果
                //同步 get() -> Future()
                //回调通知

                Future<RecordMetadata> futrue = producer.send(new ProducerRecord<>(topic, msg), (metadata, exception) -> {

                    System.out.println(metadata.offset() + "->" + metadata.partition() + "->" + metadata.topic());
                });
                futrue.get();

                TimeUnit.SECONDS.sleep(1);
                ++num;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ProducerDemo("testCopyTopic").start();
    }
}
