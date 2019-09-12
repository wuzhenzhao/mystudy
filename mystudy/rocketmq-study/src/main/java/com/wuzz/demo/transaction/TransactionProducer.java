package com.wuzz.demo.transaction;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/9/10
 * Time: 17:54
 * Description 描述:
 */
public class TransactionProducer {

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, InterruptedException {
        TransactionMQProducer transactionMQProducer=new
                TransactionMQProducer("tx_producer");
        transactionMQProducer.setNamesrvAddr("192.168.1.101:9876;192.168.1.102:9876");
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        transactionMQProducer.setExecutorService(executorService);
        transactionMQProducer.setTransactionListener(new TransactionListenerLocal()); //本地事务的监听

        transactionMQProducer.start();

        for(int i=0;i<10;i++){
            String orderId= UUID.randomUUID().toString();
            String body="{'operation':'doOrder','orderId':'"+orderId+"'}";
            Message message=new Message("testTxTopic",
                    null,orderId,body.getBytes(RemotingHelper.DEFAULT_CHARSET));
            transactionMQProducer.sendMessageInTransaction(message,orderId);
            Thread.sleep(1000);
        }
    }
}
