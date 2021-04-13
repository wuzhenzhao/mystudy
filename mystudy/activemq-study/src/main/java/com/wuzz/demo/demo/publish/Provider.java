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
                        ("admin", "hTud96KJ", "tcp://10.19.132.8:7018");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //创建目的地
//            Destination destination = session.createTopic("VirtualTopic.helloTopic");
            Destination destination = session.createTopic("ORIGIN_BAYONET_VEHICLEALARM_JSON_TOPIC");
            //创建发送者
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //创建需要发送的消息
            String msg =" {\"method\":\"OnEventNotify\",\"params\":{\"ability\":\"event_veh\",\"sendTime\":\"2021-03-16T17:02:28.077+08:00\",\"dataProcInterval\":\"108\",\"events\":[{\"data\":{\"dataProcInterval\":\"108\",\"sendTime\":\"2021-03-16T17:02:28.077+08:00\",\"picUploadInterval\":\"104\",\"channelID\":1,\"dataType\":\"vehicleAlarm\",\"dateTime\":\"2021-03-16T17:00:18.758+08:00\",\"eventDescription\":\"vehicleAlarmResult\",\"eventType\":\"vehicleAlarmResult\",\"ipAddress\":\"33.186.77.7\",\"portNo\":8000,\"recvTime\":\"2021-03-16T17:02:27.969+08:00\",\"vehicleAlarmResult\":[{\"target\":[{\"vehicle\":{\"isMainVehicle\":\"true\",\"pilotSafebelt\":{\"value\":\"unknown\"},\"pilotSunvisor\":{\"value\":\"unknown\"},\"plateColor\":{\"value\":\"blue\"},\"plateNo\":{},\"plateRect\":{\"height\":0.0,\"width\":0.0,\"x\":0.0,\"y\":0.0},\"plateType\":{\"value\":\"92TypeCivil\"},\"uphone\":{\"value\":\"unknown\"},\"vehicleColor\":{\"value\":\"black\"},\"vehicleLogo\":{\"value\":\"1123\"},\"vehicleModel\":{\"value\":\"0\"},\"vehicleSubLogo\":{\"value\":\"36\"},\"vehicleType\":{\"value\":\"vehicle\"},\"vicePilotSafebelt\":{\"value\":\"unknown\"},\"vicePilotSunvisor\":{\"value\":\"unknown\"}}}],\"targetAttrs\":{\"activeIndexCode\":\"\",\"alarmId\":\"\",\"alarmType\":\"1039\",\"areaCode\":\"e88389825017499d829fc4b9459d041a\",\"cameraAddress\":\"LG550485aQQ2东阳横店万盛南街与兴盛路西北角_YD\",\"cameraIndexCode\":\"10fc132645ac4ce199ff489f97d93b7e\",\"cameraName\":\"LG550485aQQ2东阳横店万盛南街与兴盛路西北角_YD\",\"cascade\":\"0\",\"crossingId\":1,\"crossingIndexCode\":\"a78a1a1cc19246fd91fdf5a88796e0a5\",\"crossingName\":\"万盛南街与兴盛路\",\"deviceIndexCode\":\"1147c74f6cd14f9fbc4779fc31b4a72c\",\"deviceLatitude\":\"\",\"deviceLongitude\":\"\",\"deviceName\":\"万盛南街与兴盛路\",\"deviceType\":\"100001\",\"directionIndex\":\"eastWest\",\"illegalTrafficEvent\":\"illegalParking\",\"imageServerCode\":\"0d4ce021-f5a3-4f2a-a2b9-66632e6100c6\",\"laneNo\":1,\"lanes\":[{\"directionIndex\":\"eastWest\",\"index\":1,\"laneNo\":1}],\"latitude\":\"0.0\",\"longitude\":\"0.0\",\"multiVehicle\":0,\"passID\":\"6CC987BD-95B3-4C40-801C-BE212AFD677C\",\"passTime\":\"2021-03-16T17:00:18.758+08:00\",\"platePicUrl\":\"http://33.186.77.233:6120/pic?E602E0E20CE0B4057B4F*hcs67337df1909041bab2021/bucket233/23313;161588273050743557?pic*861721*7779*61*CE08B0F50C20640868C7-2*1615885348\",\"recognitionSign\":1,\"recordBeginTime\":\"2021-03-16T17:00:18.000+08:00\",\"recordEndTime\":\"2021-03-16T17:02:26.000+08:00\",\"regionIndexCode\":\"e88389825017499d829fc4b9459d041a\",\"vehicleColorDepth\":\"0\",\"vehicleDoor\":\"close\",\"vehicleLen\":0,\"vehicleSpeed\":0},\"targetPicUrl\":\"http://33.186.77.233:6120/pic?E602E0E20CE0B4057B4F*hcs67337df1909041bab2021/bucket233/23313;161588273052340258?pic*24866148*2976792*61*CE08B0F50C20640868C7-2*1615885348\",\"taskID\":\"2207\"}]},\"eventId\":\"1750A9BB-1FEA-C643-AB28-BE6C3496405C\",\"eventType\":131684,\"happenTime\":\"2021-03-16T17:00:18.758+08:00\",\"srcIndex\":\"10fc132645ac4ce199ff489f97d93b7e\",\"srcType\":\"camera\",\"srcParentIndex\":\"1147c74f6cd14f9fbc4779fc31b4a72c\",\"status\":0,\"timeout\":0}]}}";
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
