package com.wuzz.demo.message.ttl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/8/26
 * Time: 14:42
 * Description 描述:
 */
public class TTLQueueCreate {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        // 连接IP
        factory.setHost("192.168.1.101");
        // 连接端口
        factory.setPort(5672);
        // 虚拟机
        factory.setVirtualHost("/");
        // 用户
        factory.setUsername("guest");
        factory.setPassword("guest");

        // 建立连接
        Connection conn = factory.newConnection();
        // 创建消息通道
        Channel channel = conn.createChannel();
        Map<String, Object> arguments = new HashMap<String, Object>();
        arguments.put("x-dead-letter-exchange", "DLX_EXCHANGE");
        // 通过队列属性设置消息过期时间
        arguments.put("x-message-ttl", 20000);
        // 指定了这个队列的死信交换机(消息发送到这个队列)
        channel.queueDeclare("TEST_DLX_QUEUE", false, false, false, arguments);
        // 声明死信交换机
        channel.exchangeDeclare("DLX_EXCHANGE", "topic", false, false, false, null);
        // 声明死信队列
        channel.queueDeclare("DLX_QUEUE", false, false, false, null);
        // 绑定
        channel.queueBind("DLX_QUEUE", "DLX_EXCHANGE", "#");

        channel.close();
        conn.close();
    }
}
