package com.wuzz.demo.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/8/22
 * Time: 21:17
 * Description 描述:
 */
public class ProducerDemo extends Thread {

    private final KafkaProducer<String, String> producer;

    private final String topic;

    public ProducerDemo(String topic) {
        Properties properties = new Properties();
        // 连接的 kafka 集群地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "192.168.1.101:9092");//,192.168.1.102:9092,192.168.1.103:9092
        // 客户端ID标识
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaProducerDemo");
        //确认记录，保证记录不丢失 总是设置成-1
        properties.put(ProducerConfig.ACKS_CONFIG, "-1");
        // 键序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.IntegerSerializer");
        //值序列化
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        //Producer自动升级成幂等性Producer。Kafka会自动去重。
//        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
//        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "tx-id");
        //指定自己的分区规则
//        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.wuzz.demo.demo.MyPartition");
        producer = new KafkaProducer<String, String>(properties);
        this.topic = topic;


    }

    @Override
    public void run() {
        int num = 0;
//        while(num<1) {
        try {

            // 方法 1: 使用 callback
//                producer.send(new ProducerRecord<String, String>("topic0", "message 2"), new Callback() {
//
//                    public void onCompletion(RecordMetadata metadata, Exception exception) {
//                        if(exception != null) {
//                            System.out.println("send message2 failed with " + exception.getMessage());
//                        } else {
//                            // offset 是消息在 partition 中的编号，可以根据 offset 检索消息
//                            System.out.println("message2 sent to " + metadata.topic() + ", partition " + metadata.partition() + ", offset " + metadata.offset());
//                        }
//
//                    }
//
//                });
            String msg = "{\"method\":\"OnEventNotify\",\"params\":{\"ability\":\"event_city_mgr\",\"events\":[{\"data\":{\"Result\":[{\"SceneId\":1,\"Target\":[{\"Rect\":{\"height\":0.07083333283662796,\"width\":0.04843750223517418,\"x\":0.5453125238418579,\"y\":0.3138889074325562}}],\"backgroundImageURL\":\"http://33.186.77.233:6120/pic?E602E0E20CE0B4057B4F*hcs67337df1909041bab2021/bucket233/23313;1615983848378215134?pic*12820266*534974*45*E602E0E20CE0B4057B4F-2*1615984822\",\"ruleID\":1,\"subEventType\":\"hangClothingAlongStreet\"}],\"activePostCount\":1,\"channelID\":1,\"channelName\":\"LG556118aQQ2华夏大道与济慈路路口_YD\",\"dataProcInterval\":\"14\",\"dataType\":\"cityManagement\",\"dateTime\":\"2021-03-17T20:40:22.000+08:00\",\"deviceID\":88,\"eventDescription\":\"City Management\",\"eventState\":\"active\",\"eventType\":\"cityManagement\",\"ipAddress\":\"33.186.77.183\",\"ipv6Address\":\"\",\"macAddress\":\"2c:a5:9c:26:45:61\",\"picUploadInterval\":\"10\",\"portNo\":8000,\"protocol\":\"HTTP\",\"recvTime\":\"2021-03-17T20:40:22.602+08:00\",\"sendTime\":\"2021-03-17T20:40:22.616+08:00\",\"targetAttrs\":{\"cameraAddress\":\"LG556118aQQ2华夏大道与济慈路路口_YD\",\"cameraIndexCode\":\"db4ff43a8d614b5ebcee49483347e453\",\"cameraName\":\"LG556118aQQ2华夏大道与济慈路路口_YD\",\"deviceIndexCode\":\"5f7cd0123303495f8315f6b30a119a75\",\"deviceName\":\"华夏大道与济慈路城管球机\",\"deviceType\":\"100001\",\"imageServerCode\":\"0d4ce021-f5a3-4f2a-a2b9-66632e6100c6\",\"latitude\":\"0.0\",\"longitude\":\"0.0\",\"recognitionSign\":1,\"regionIndexCode\":\"5209efc81b4e485f9544077b1f9c0c51\"}},\"eventId\":\"482D8FB1-E67E-7645-929B-3C508D2B133A\",\"eventType\":1503234,\"happenTime\":\"2021-03-17T20:40:22+08:00\",\"srcIndex\":\"db4ff43a8d614b5ebcee49483347e453\",\"srcParentIndex\":\"5f7cd0123303495f8315f6b30a119a75\",\"srcType\":\"camera\",\"status\":0,\"timeout\":0}],\"sendTime\":\"2021-03-17T20:40:22.616+08:00\"}}";
            //get 会拿到发送的结果
            //同步 get() -> Future()
            //回调通知

            Future<RecordMetadata> futrue = producer.send(new ProducerRecord<>(topic, msg), (metadata, exception) -> {

                System.out.println(metadata.offset() + "->" + metadata.partition() + "->" + metadata.topic());
            });
            futrue.get();

            TimeUnit.SECONDS.sleep(1);
            ++num;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // kafka  事务型消息
//            producer.initTransactions();
//            try {
//                producer.beginTransaction();
//                producer.send(record1);
//                producer.send(record2);
//                producer.commitTransaction();
//            } catch (KafkaException e) {
//                producer.abortTransaction();
//            }
//        }
    }

    public static void main(String[] args) {
        new ProducerDemo("test_topic").start();
    }
}
