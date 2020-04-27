package com.wuzz.demo.demo;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

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
        //new ActiveMQConnectionFactory("tcp://192.168.11.153:61616?jms.useAsyncSend=true");
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://192.168.1.101:61616");
        Connection connection = null;
        try {

            connection = connectionFactory.createConnection();
            connection.start();
            // 延迟确认
            Session session = connection.createSession(Boolean.TRUE, Session.DUPS_OK_ACKNOWLEDGE);
            // 创建目的地
            Destination destination = session.createQueue("stringQueue");
            // 创建消费者
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage("Hello World");
            //延迟60秒发送消息
//            long time = 60 * 1000;
//            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
            //开始延迟30秒发送，重复发送10次，每次之间间隔10秒
//            long delay = 30 * 1000;
//            long period = 10 * 1000;
//            int repeat = 9;
//            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
//            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
//            message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);
            //使用Cron 表示式定时发送消息
//            message.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_CRON, "0 * * * *");
            //Cron 的优先级大于消息延迟，只要设置了Cron 表达式会优先执行Cron规则，如下：消息定时发送10次，每个小时执行，延迟1秒之后发送。

//            message.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_CRON, "0 * * * *");
//            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 1000);
//            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, 1000);
//            message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, 9);
            producer.send(message);
            // 表示消息被自动确认
            session.commit();
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
