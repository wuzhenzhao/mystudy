package com.wuzz.demo.ack;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wuzz.demo.message.ResourceUtil;

/**
 * @Author:
 * @Date: 2018/9/21 10:52
 * @Description: 消息生产者，用于测试消费者手工应答和重回队列
 */
public class AckProducer {
    private final static String QUEUE_NAME = "TEST_ACK_QUEUE";

    public static void main(String[] args) throws Exception {
        try {
            ConnectionFactory factory = new ConnectionFactory();
//            factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));
        //amqp://root:wYgmafFg@10.33.43.14:6005
            // 连接IP
            factory.setHost("10.33.43.14");
            // 连接端口
            factory.setPort(6005);
            // 虚拟机
//			factory.setVirtualHost("/");
            // 用户
            factory.setUsername("root");
            factory.setPassword("wYgmafFg");
//            factory.setConnectionTimeout(30000);
            factory.setHandshakeTimeout(30000);
            // 建立连接
            Connection conn = factory.newConnection();
            // 创建消息通道
            Channel channel = conn.createChannel();

            String msg = "test ack message ";
            // 声明队列（默认交换机AMQP default，Direct）
            // String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // 发送消息
            // String exchange, String routingKey, BasicProperties props, byte[] body
            for (int i = 0; i < 5; i++) {
                channel.basicPublish("", QUEUE_NAME, null, (msg + i).getBytes());
            }

            channel.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

