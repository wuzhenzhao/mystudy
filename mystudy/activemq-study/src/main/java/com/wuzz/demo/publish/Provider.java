package com.wuzz.demo.publish;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/5/5
 * Time: 14:54
 * Description 描述:
 */
public class Provider {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory
                        ("tcp://192.168.1.101:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //创建目的地
            Destination destination = session.createTopic("myTopic");
            //创建发送者
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //创建需要发送的消息
            TextMessage message = session.createTextMessage("topic -message");
            //Text   Map  Bytes  Stream  Object
            producer.send(message);
            session.commit();
//            session.rollback();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
