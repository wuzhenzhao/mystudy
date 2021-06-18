package com.wuzz.demo;

import org.apache.pulsar.client.api.CompressionType;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

import java.util.concurrent.TimeUnit;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2021/4/30 16:12
 * @since 1.0
 **/
public class PulsarProducer {

    public static void main(String[] args) {
        Producer<byte[]> producer = null;
        try {
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl("pulsar://192.168.1.101:6650")
                    .build();

            producer = client.newProducer()
                    .topic("my-topic")
                    .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
                    .sendTimeout(10, TimeUnit.SECONDS)
                    .blockIfQueueFull(true) // 发送模式
//                    .compressionType(CompressionType.LZ4) //压缩模型
//                    .enableChunking(true) // 分块
                    .create();

            // 然后你就可以发送消息到指定的broker 和topic上：
//            producer.send("My message".getBytes());
            //异步发送
//            producer.sendAsync("my-async-message".getBytes()).thenAccept(msgId -> {
//                System.out.printf("Message with ID %s successfully sent", msgId);
//            });
                //除了一个值，你可以在一个给定的消息上设置额外的项:
            producer.newMessage()
                    .key("my-message-key")
                    .value("my-async-message".getBytes())
                    .property("my-key", "my-value")
                    .property("my-other-key", "my-other-value")
                    .send();

            client.close();
        } catch (PulsarClientException e) {
            e.printStackTrace();
        } finally {
            producer.closeAsync().thenRun(() -> {
                System.out.println("Producer closed");
            });

        }
    }
}
