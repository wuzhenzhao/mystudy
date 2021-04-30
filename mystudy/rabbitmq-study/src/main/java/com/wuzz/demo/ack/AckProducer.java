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
            factory.setHost("10.19.189.56");
            // 连接端口
            factory.setPort(6005);
            // 虚拟机
//			factory.setVirtualHost("/");
            // 用户
            factory.setUsername("root");
            factory.setPassword("wdEfEeC7");
//            factory.setConnectionTimeout(30000);
            factory.setHandshakeTimeout(30000);
            // 建立连接
            Connection conn = factory.newConnection();
            // 创建消息通道
            Channel channel = conn.createChannel();

            String msg ="{\"method\":\"OnEventNotify\",\"params\":{\"ability\":\"event_city_mgr\",\"events\":[{\"data\":{\"Result\":[{\"SceneId\":1,\"Target\":[{\"Rect\":{\"height\":0.07083333283662796,\"width\":0.04843750223517418,\"x\":0.5453125238418579,\"y\":0.3138889074325562}}],\"backgroundImageURL\":\"http://33.186.77.233:6120/pic?E602E0E20CE0B4057B4F*hcs67337df1909041bab2021/bucket233/23313;1615983848378215134?pic*12820266*534974*45*E602E0E20CE0B4057B4F-2*1615984822\",\"ruleID\":1,\"subEventType\":\"hangClothingAlongStreet\"}],\"activePostCount\":1,\"channelID\":1,\"channelName\":\"LG556118aQQ2华夏大道与济慈路路口_YD\",\"dataProcInterval\":\"14\",\"dataType\":\"cityManagement\",\"dateTime\":\"2021-03-17T20:40:22.000+08:00\",\"deviceID\":88,\"eventDescription\":\"City Management\",\"eventState\":\"active\",\"eventType\":\"cityManagement\",\"ipAddress\":\"33.186.77.183\",\"ipv6Address\":\"\",\"macAddress\":\"2c:a5:9c:26:45:61\",\"picUploadInterval\":\"10\",\"portNo\":8000,\"protocol\":\"HTTP\",\"recvTime\":\"2021-03-17T20:40:22.602+08:00\",\"sendTime\":\"2021-03-17T20:40:22.616+08:00\",\"targetAttrs\":{\"cameraAddress\":\"LG556118aQQ2华夏大道与济慈路路口_YD\",\"cameraIndexCode\":\"db4ff43a8d614b5ebcee49483347e453\",\"cameraName\":\"LG556118aQQ2华夏大道与济慈路路口_YD\",\"deviceIndexCode\":\"5f7cd0123303495f8315f6b30a119a75\",\"deviceName\":\"华夏大道与济慈路城管球机\",\"deviceType\":\"100001\",\"imageServerCode\":\"0d4ce021-f5a3-4f2a-a2b9-66632e6100c6\",\"latitude\":\"0.0\",\"longitude\":\"0.0\",\"recognitionSign\":1,\"regionIndexCode\":\"5209efc81b4e485f9544077b1f9c0c51\"}},\"eventId\":\"482D8FB1-E67E-7645-929B-3C508D2B133A\",\"eventType\":1503234,\"happenTime\":\"2021-03-17T20:40:22+08:00\",\"srcIndex\":\"db4ff43a8d614b5ebcee49483347e453\",\"srcParentIndex\":\"5f7cd0123303495f8315f6b30a119a75\",\"srcType\":\"camera\",\"status\":0,\"timeout\":0}],\"sendTime\":\"2021-03-17T20:40:22.616+08:00\"}}";
            // 声明队列（默认交换机AMQP default，Direct）
            // String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.exchangeDeclare("ianalysis.iac.xalarm.exchange","direct",true);
            channel.basicPublish("ianalysis.iac.xalarm.exchange","ianalysis.iac",null,msg.getBytes());
            // 发送消息
            // String exchange, String routingKey, BasicProperties props, byte[] body
//            for (int i = 0; i < 5; i++) {
//                channel.basicPublish("", QUEUE_NAME, null, (msg + i).getBytes());
//            }

            channel.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

