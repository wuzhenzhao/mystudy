package com.wuzz.demo.demo;

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
public class RocketMqConsumer {
    public static void main(String[] args) throws MQClientException {
        //消费者的组名，这个和kafka是一样,这里需要注意的是，
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("unique_consumer_group_name");
        //指定NameServer地址，多个地址以 ; 隔开
        consumer.setNamesrvAddr("192.168.1.101:9876");
        //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
        //如果非第一次启动，那么按照上次消费的位置继续消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //订阅PushTopic下Tag为push的消息
        consumer.subscribe("testTopicQueueNums", "*"); //*表示不过滤，可以通过tag来过滤，比如:”tagA”
        /*
         * 注册消息监听回调这里有两种监听，MessageListenerConcurrently以及MessageListenerOrderly
         * 前者是普通监听，后者是顺序监听。这块在后续单独说明
         */
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt>
                                                                    msgs,
                                                            ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n",
                        Thread.currentThread().getName(), msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;//返回消息消费状态
            }
        });
        consumer.start();
        System.out.printf("Consumer Started.%n");

    }
}
