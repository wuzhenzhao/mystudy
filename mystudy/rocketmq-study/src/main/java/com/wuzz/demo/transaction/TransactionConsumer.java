package com.wuzz.demo.transaction;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/9/10
 * Time: 17:54
 * Description 描述:
 */
public class TransactionConsumer {

    //rocketMQ 除了在同一个组和不同组之间的消费者的特性和kafka相同之外
    //RocketMQ可以支持广播消息，就意味着，同一个group的每个消费者都可以消费同一个消息


    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer defaultMQPushConsumer=
                new DefaultMQPushConsumer("tx_consumer");
        defaultMQPushConsumer.setNamesrvAddr("192.168.1.101:9876;192.168.1.102:9876");
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //subExpression 可以支持sql的表达式. or  and  a=? ,,,
        defaultMQPushConsumer.subscribe("testTxTopic","*");
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                list.stream().forEach(message->{
                    System.out.println("开始业务处理逻辑：消息体："+new String(message.getBody())+"->key:"+message.getKeys());
                });
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //签收
            }
        });
        defaultMQPushConsumer.start();
    }

}
