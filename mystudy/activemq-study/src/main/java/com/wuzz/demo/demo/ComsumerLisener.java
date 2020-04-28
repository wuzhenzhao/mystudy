package com.wuzz.demo.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/5/5
 * Time: 16:51
 * Description 描述:
 */
public class ComsumerLisener {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory
                        ("tcp://192.168.1.101:61616");
        Connection connection = null;
        try {

            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession
                    (Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
//            Session session = connection.createSession
//                    (Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
            //创建目的地
//            Destination destination = session.createQueue("VirtualTopicConsumers.A.VirtualTopic.helloTopic");
            Destination destination = session.createQueue("myQueue");
            //创建接收者
            MessageConsumer consumer = session.createConsumer(destination);


            MessageListener messageListener = new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println(((TextMessage) message).getText());
                        session.commit();
//                        session.rollback();
//                        session.recover();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            };

            consumer.setMessageListener(messageListener);
            System.in.read();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
