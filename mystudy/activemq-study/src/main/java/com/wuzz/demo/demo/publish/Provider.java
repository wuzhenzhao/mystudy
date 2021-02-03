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
                        ("admin", "74OXqH6w", "tcp://10.19.132.8:7018");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //创建目的地
//            Destination destination = session.createTopic("VirtualTopic.helloTopic");
            Destination destination = session.createTopic("iac.ifoottraffic.flow_density_data.topic");
            //创建发送者
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //创建需要发送的消息
            String msg ="{\"method\":\"OnEventNotify\",\"params\":{\"ability\":\"event_pdc\",\"sendTime\":\"2021-01-19T09:36:32.486+08:00\",\"events\":[{\"data\":{\"sendTime\":\"2021-01-19T09:36:32.485+08:00\",\"channelID\":1,\"dataType\":\"flowStatistic\",\"dateTime\":\"2021-01-17T14:45:00.000+08:00\",\"eventDescription\":\"peopleCounting\",\"eventType\":\"peopleCounting\",\"ipAddress\":\"10.238.24.21\",\"peopleCounting\":[{\"ageGroupList\":[{\"ageGroup\":{\"count\":0,\"value\":\"child\"}},{\"ageGroup\":{\"count\":0,\"value\":\"kid\"}},{\"ageGroup\":{\"count\":0,\"value\":\"teenager\"}},{\"ageGroup\":{\"count\":0,\"value\":\"young\"}},{\"ageGroup\":{\"count\":0,\"value\":\"prime\"}},{\"ageGroup\":{\"count\":0,\"value\":\"middle\"}},{\"ageGroup\":{\"count\":1,\"value\":\"middleAged\"}},{\"ageGroup\":{\"count\":1,\"value\":\"old\"}},{\"ageGroup\":{\"count\":0,\"value\":\"unknown\"}}],\"childEnterNum\":0,\"childLeaveNum\":0,\"duplicatePeople\":2,\"enter\":4,\"exit\":4,\"faceExpressionList\":[{\"faceExpression\":{\"count\":0,\"value\":\"surprised\"}},{\"faceExpression\":{\"count\":0,\"value\":\"panic\"}},{\"faceExpression\":{\"count\":0,\"value\":\"disgusted\"}},{\"faceExpression\":{\"count\":2,\"value\":\"happy\"}},{\"faceExpression\":{\"count\":0,\"value\":\"sad\"}},{\"faceExpression\":{\"count\":0,\"value\":\"angry\"}},{\"faceExpression\":{\"count\":0,\"value\":\"poker-faced\"}},{\"faceExpression\":{\"count\":0,\"value\":\"unknown\"}}],\"genderList\":[{\"gender\":{\"count\":2,\"value\":\"male\"}},{\"gender\":{\"count\":0,\"value\":\"female\"}},{\"gender\":{\"count\":0,\"value\":\"unknown\"}}],\"glassList\":[{\"glass\":{\"count\":2,\"value\":\"no\"}},{\"glass\":{\"count\":0,\"value\":\"yes\"}},{\"glass\":{\"count\":0,\"value\":\"sunglasses\"}},{\"glass\":{\"count\":0,\"value\":\"unknown\"}}],\"maskList\":[{\"mask\":{\"count\":2,\"value\":\"no\"}},{\"mask\":{\"count\":0,\"value\":\"yes\"}},{\"mask\":{\"count\":0,\"value\":\"unknown\"}}],\"pass\":0,\"statisticalMethods\":\"timeRange\",\"targetAttrs\":{\"cameraAddress\":\"马岙文化礼堂密度\",\"cameraIndexCode\":\"f383dfb36502481f8103954aeaf9f51c\",\"cameraName\":\"马岙文化礼堂密度\",\"deviceIndexCode\":\"11c156dafd9f48339d61a2e55077ed76\",\"deviceName\":\"IP CAMERA\",\"deviceType\":100001,\"recognitionSign\":1,\"regionIndexCode\":\"c8ef86cd43c0445fa70dc3a72f2692d8\"},\"timeRange\":{\"endTime\":\"2021-01-17T15:00:00.000+08:00\",\"startTime\":\"2021-01-17T14:45:00.000+08:00\"}}],\"portNo\":8000,\"recvTime\":\"2021-01-19T09:36:32.469+08:00\"},\"eventId\":\"10D04BCF-EA63-B340-9D37-32079CBEE911\",\"eventType\":131616,\"happenTime\":\"2021-01-17T14:45:00.000+08:00\",\"srcIndex\":\"f383dfb36502481f8103954aeaf9f51c\",\"srcType\":\"camera\",\"srcParentIndex\":\"11c156dafd9f48339d61a2e55077ed76\",\"status\":0,\"timeout\":0}]}}";
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
