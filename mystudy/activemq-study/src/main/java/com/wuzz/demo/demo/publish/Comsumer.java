package com.wuzz.demo.demo.publish;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/5/5
 * Time: 16:51
 * Description 描述:
 */
public class Comsumer {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory
                        ("tcp://192.168.1.101:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.setClientID("wuzz");
            connection.start();
            Session session = connection.createSession
                    (Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //创建目的地
            Topic destination = session.createTopic("myTopic");
            //创建接收者
            MessageConsumer consumer = session.createDurableSubscriber(destination, "wuzz");
            TextMessage textMessage = (TextMessage) consumer.receive();
            System.out.println(textMessage.getText());
            session.commit(); //消息被确认
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
