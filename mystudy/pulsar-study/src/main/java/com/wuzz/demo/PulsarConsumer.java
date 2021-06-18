package com.wuzz.demo;

import org.apache.pulsar.client.api.*;

import java.util.concurrent.TimeUnit;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2021/4/30 15:59
 * @since 1.0
 **/
public class PulsarConsumer extends Thread {

    private Consumer consumer;

    public PulsarConsumer() {
        try {
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl("pulsar://192.168.1.101:6650")
                    .build();
            //灾备
//            consumer = client.newConsumer()
//                    .topic("my-topic")
//                    .subscriptionName("my-subscription")
//                    .subscriptionType(SubscriptionType.Failover)
//                    .subscribe();

            //死信队列
//            consumer = client.newConsumer(Schema.BYTES)
//                    .topic("my-topic")
//                    .subscriptionName("my-subscription")
//                    .subscriptionType(SubscriptionType.Shared)
//                    .ackTimeout(3, TimeUnit.SECONDS)
//                    .receiverQueueSize(100)
//                    .deadLetterPolicy(DeadLetterPolicy.builder() //启用死信队列功能
//                            .maxRedeliverCount(1)//在进入死信队列之前，消息最多被重新投递的最大次数
//                            .build())
//                    .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
//                    .subscribe();

//            client.newConsumer()
//                    .topic("my-topic")
//                    .messageListener(new MessageListener<byte[]>() {
//                        @Override
//                        public void received(Consumer<byte[]> consumer, Message<byte[]> message) {
//                            System.out.printf("Message received MessageListener: %s", new String(message.getData()));
////                            try {
////                                consumer.acknowledge(message);
////                            } catch (PulsarClientException e) {
////                                e.printStackTrace();
////                            }
//
//                            if("my-async-message".equals(new String(message.getData()))){
//                                consumer.negativeAcknowledge(message);
//                            }
//                        }
//                    })
//                    .subscriptionName("my-subscription")
//                    .ackTimeout(5, TimeUnit.SECONDS)
//                    .deadLetterPolicy(DeadLetterPolicy.builder()
//                            .maxRedeliverCount(1)
//                            .deadLetterTopic("my-subscription-dead")
//                            .build())
//                    .subscribe();

        } catch (PulsarClientException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        PulsarConsumer pulsarConsumer = new PulsarConsumer();
        pulsarConsumer.start();

    }

    @Override
    public void run() {
        while (true) {

            Message msg = null;
            try {
                // Wait for a message
                msg = consumer.receive();
//                consumer.resume(); // 重新消费
                // 异步接收
//                CompletableFuture<Message> asyncMessage = consumer.receiveAsync();

                // 批量接收
//                Messages messages = consumer.batchReceive();
//                for (Object message : messages) {
//                    // do something
//                }
//                consumer.acknowledge(messages);
                // Do something with the message
                System.out.printf("Message received: %s", new String(msg.getData()));

                // Acknowledge the message so that it can be deleted by the message broker
//                consumer.acknowledge(msg);
            } catch (Exception e) {
                // Message failed to process, redeliver later
                consumer.negativeAcknowledge(msg);
            }
        }
    }
}
