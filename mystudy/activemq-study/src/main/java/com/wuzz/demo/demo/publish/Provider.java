package com.wuzz.demo.demo.publish;


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
//        ConnectionFactory connectionFactory =
//                new ActiveMQConnectionFactory
//                        ("tcp://192.168.1.101:61616");
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory
                        ("admin", "j1Snd2Bm", "tcp://10.19.189.56:7018");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //创建目的地
//            Destination destination = session.createTopic("VirtualTopic.helloTopic");
            Destination destination = session.createTopic("iac.esc.thermalImaging.topic");
            //创建发送者
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //创建需要发送的消息
            String msg ="{\"method\":\"OnEventNotify\",\"params\":{\"ability\":\"event_heat\",\"sendTime\":\"2021-04-27T16:51:39.303+08:00\",\"events\":[{\"data\":{\"sendTime\":\"2021-04-27T16:51:39.303+08:00\",\"channelID\":35,\"dataType\":\"thermalImaging\",\"dateTime\":\"2021-04-27T16:51:37.000+08:00\",\"eventDescription\":\"fireDetection\",\"eventType\":\"fireDetection\",\"fireDetection\":[{\"fireMaxTemperature\":110,\"fireRegion\":{\"height\":\"0.008\",\"width\":\"0.006\",\"x\":\"0.918\",\"y\":\"0.525\"},\"fireScanWaitMode\":\"auto\",\"highestPoint\":{\"x\":\"0.931\",\"y\":\"0.541\"},\"imageUrl\":\"http://59.202.82.56:6120/pic?AC01F06B07E09704A334*hcs8544c8a548d64271a_2020/123456/7529;161951102678675018053?pic*500824547*10116*16113*AC01F06B07E09704A334-2*1619513425\",\"ptzInfo\":{\"focus\":\"0\",\"pan\":\"0\",\"tilt\":\"0\",\"zoom\":\"0\"},\"regionID\":1,\"targetAttrs\":{\"imageServerCode\":\"b94c70cd-bd51-4547-91da-6713ef8d83f5\",\"cameraAddress\":\"Camera 02\",\"cameraIndexCode\":\"17c88366ec0640bc9012b1beb6fe207f\",\"cameraName\":\"Camera 02\",\"deviceIndexCode\":\"0ce23823c9ee4beea7ae433ea7a9288a\",\"deviceName\":\"头号玩家网吧\",\"deviceType\":\"100001\",\"latitude\":\"0.0\",\"longitude\":\"0.0\",\"recognitionSign\":1,\"regionIndexCode\":\"5132b111b7d54be1bb765f3775a18bd7\"},\"targetDistance\":0,\"temperatureUnit\":\"celsius\",\"visiblePicUrl\":\"http://59.202.82.56:6120/pic?AC01F06B07E09704A334*hcs8544c8a548d64271a_2020/123456/7529;161951318456388618056?pic*519552362*329144*1903*AC01F06B07E09704A334-2*1619513425\"}],\"ipAddress\":\"10.100.1.2\",\"portNo\":8000,\"recvTime\":\"2021-04-27T16:51:39.293+08:00\"},\"eventId\":\"2200F074-F101-DA46-9FEE-EB9D771BA120\",\"eventType\":192515,\"happenTime\":\"2021-04-27T16:51:37.000+08:00\",\"srcIndex\":\"17c88366ec0640bc9012b1beb6fe207f\",\"srcType\":\"camera\",\"srcParentIndex\":\"0ce23823c9ee4beea7ae433ea7a9288a\",\"status\":0,\"timeout\":0}]}}";
            TextMessage message = session.createTextMessage(msg);
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
