package com.wuzz.demo.demo.publish;

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
public class Comsumer {

    public static void main(String[] args) {
//        ConnectionFactory connectionFactory =
//                new ActiveMQConnectionFactory
//                        ("tcp://192.168.1.101:61616");

        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory
                        ("admin","NikCxkoi","tcp://10.19.132.8:7018");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.setClientID("wuzz");
            connection.start();
            Session session = connection.createSession
                    (Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //创建目的地
            Topic destination = session.createTopic("iac.ifoottraffic.flow_density_data.topic");
//            Topic destination = session.createTopic("cem.cemweb.entrance.guard.sub.event.topic");
//            Topic destination = session.createTopic("iac.esc.thermalImaging.topic");
            //创建接收者
            MessageConsumer consumer = session.createDurableSubscriber(destination, "wuzz");
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
//            TextMessage textMessage = (TextMessage) consumer.receive();
//            System.out.println(textMessage.getText());
//            session.commit(); //消息被确认
//            session.close();
        } catch (JMSException | IOException e) {
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
