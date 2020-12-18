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
                        ("admin", "NikCxkoi", "tcp://10.19.132.8:7018");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //创建目的地
//            Destination destination = session.createTopic("VirtualTopic.helloTopic");
            Destination destination = session.createTopic("cem.cemweb.entrance.guard.sub.event.topic");
            //创建发送者
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //创建需要发送的消息
//            String msg = "{\"method\":\"OnEventNotify\",\"params\":{\"ability\":\"event_pdc\",\"sendTime\":\"2020-12-16T20:00:36.334+08:00\",\"events\":[{\"data\":{\"sendTime\":\"2020-12-16T20:00:36.331+08:00\",\"channelID\":1,\"dataType\":\"flowStatistic\",\"dateTime\":\"2034-01-01T00:00:00.000+08:00\",\"eventDescription\":\"peopleCounting\",\"eventType\":\"peopleCounting\",\"ipAddress\":\"172.7.27.250\",\"peopleCounting\":[{\"childEnterNum\":0,\"childLeaveNum\":0,\"duplicatePeople\":0,\"enter\":0,\"exit\":0,\"pass\":0,\"statisticalMethods\":\"timeRange\",\"targetAttrs\":{\"activeIndexCode\":\"\",\"cameraAddress\":\"Camera 01\",\"cameraIndexCode\":\"d1313a5dcb7a4087bdaea888bd758476\",\"cameraName\":\"Camera 01\",\"deviceIndexCode\":\"0f5271e92b134c2f889616ab17d2bc52\",\"deviceLatitude\":\"\",\"deviceLongitude\":\"\",\"deviceName\":\"IP CAMERA\",\"deviceType\":100001,\"latitude\":\"\",\"longitude\":\"\",\"recognitionSign\":1,\"regionIndexCode\":\"ded34b24f37240dc9ca7a962c918d45f\"},\"timeRange\":{\"endTime\":\"2034-01-01T03:34:01.000+08:00\",\"startTime\":\"2034-01-01T00:00:00.000+08:00\"}}],\"portNo\":8000,\"recvTime\":\"2020-12-16T20:00:36.330+08:00\"},\"eventId\":\"68F2A6C4-F576-D845-A58A-DDECD89FDF26\",\"eventType\":131616,\"happenTime\":\"2034-01-01T00:00:00.000+08:00\",\"srcIndex\":\"d1313a5dcb7a4087bdaea888bd758476\",\"srcType\":\"camera\",\"srcParentIndex\":\"0f5271e92b134c2f889616ab17d2bc52\",\"status\":0,\"timeout\":0}]}}";
            String msg ="{\"method\":\"OnEventNotify\",\"params\":{\"ability\":\"event_acs\",\"clients\":[\"acs\",\"cem\"],\"events\":[{\"data\":{\"ExtAccessChannel\":0,\"ExtDeviceNo\":0,\"ExtEventAlarmInID\":0,\"ExtEventAlarmOutID\":0,\"ExtEventCardNo\":\"0695915616\",\"ExtEventCaseID\":0,\"ExtEventCode\":196893,\"ExtEventCustomerNumInfo\":{\"AccessChannel\":0,\"EntryTimes\":0,\"ExitTimes\":0,\"TotalTimes\":0},\"ExtEventDoorID\":1,\"ExtEventIDCardPictureURL\":\"\",\"ExtEventIdentityCardInfo\":{\"Address\":\"\",\"Birth\":\"\",\"EndDate\":\"\",\"IdNum\":\"\",\"IssuingAuthority\":\"\",\"Name\":\"\",\"Nation\":0,\"Sex\":0,\"StartDate\":\"\",\"TermOfValidity\":0},\"ExtEventInOut\":1,\"ExtEventLocalControllerID\":0,\"ExtEventMainDevID\":1,\"ExtEventPersonNo\":\"0\",\"ExtEventPictureURL\":\"\",\"ExtEventReaderID\":1,\"ExtEventReaderKind\":0,\"ExtEventReportChannel\":0,\"ExtEventRoleID\":0,\"ExtEventSubDevID\":0,\"ExtEventSwipNum\":0,\"ExtEventType\":0,\"ExtEventVerifyID\":0,\"ExtEventWhiteListNo\":0,\"ExtReceiveTime\":\"1608191648132513\",\"Seq\":11376,\"svrIndexCode\":\"\"},\"eventId\":\"E2D61699-B616-47FE-BAF9-7D8529A84738\",\"eventType\":196893,\"eventTypeName\":\"acs.acs.eventType.successFace\",\"happenTime\":\"2020-12-17T15:55:03.000+08:00\",\"srcIndex\":\"2347b2859d254431a776f3cb38361c2c\",\"srcName\":\"cem门禁测试_门1\",\"srcParentIndex\":\"30d89b4c72ef494c9d03d2666deedb82\",\"srcType\":\"door\",\"status\":0,\"timeout\":0}],\"sendTime\":\"2020-12-17T15:54:08.000+08:00\",\"uids\":[\"admin\"]}}";
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
