package com.wuzz.demo;

import org.apache.pulsar.client.api.*;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2021/4/30 15:59
 * @since 1.0
 **/
public class PulsarDeadletterConsumer extends Thread {

    private Consumer consumer;

    public PulsarDeadletterConsumer() {
        try {
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl("pulsar://192.168.1.101:6650")
                    .build();

//            consumer = client.newConsumer()
//                    .topic("my-topic")
//                    .subscriptionName("my-subscription")
//                    .subscribe();

            client.newConsumer()
                    .topic("my-topic-my-subscription-DLQ")
                    .messageListener(new MessageListener<byte[]>() {
                        @Override
                        public void received(Consumer<byte[]> consumer, Message<byte[]> message) {
                            System.out.printf("Message received MessageListener: %s", new String(message.getData()));
                        }
                    })
                    .subscriptionName("my-subscription")
                    .subscribe();

        } catch (PulsarClientException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        PulsarDeadletterConsumer pulsarConsumer = new PulsarDeadletterConsumer();
//        pulsarConsumer.start();

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
                consumer.acknowledge(msg);
            } catch (Exception e) {
                // Message failed to process, redeliver later
                consumer.negativeAcknowledge(msg);
            }
        }
    }
}
