package com.wuzz.demo.demo;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/9/10
 * Time: 17:54
 * Description 描述:
 */
public class RocketMqProducer {


    public static void main(String[] args) throws MQClientException, InterruptedException {
        /*
         *生产者组，简单来说就是多个发送同一类消息的生产者称之为一个生产者组
         *rocketmq支持事务消息，在发送事务消息时，如果事务消息异常（producer挂了），broker端会来回查
         *事务的状态，这个时候会根据group名称来查找对应的producer来执行相应的回查逻辑。相当于实现了producer的高可用
         */
        DefaultMQProducer producer = new DefaultMQProducer("unique_producer_group_name");
        //指定namesrv服务地址，获取broker相关信息
        producer.setNamesrvAddr("192.168.1.101:9876");
        producer.start();
        //设置重发次数
//        producer.setRetryTimesWhenSendAsyncFailed(5);
        for (int i = 0; i < 10; i++) {
            try {
                //创建一个消息实例，指定指定topic、tag、消息内容
                Message msg = new Message("testTopic", "testTag",
                        ("Hello RocketMQ? " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */);
                //发送消息并且获取发送结果
                SendResult sendResult = producer.send(msg);
                System.out.printf("%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        producer.shutdown();
    }
}
