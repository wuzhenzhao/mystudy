package com.wuzz.demo.integratedway1;

import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Session;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/9
 * Time: 17:06
 * Description:
 * ClassPath:com.wuzz.demo.integratedway1.QueueConsumer
 */
@Component
public class QueueConsumer {


    @JmsListener(destination = "stringQueue", containerFactory = "jmsListenerContainerQueue")
    public void consumer(ActiveMQMessage message, Session session) throws Exception {
        try {
            System.out.println(session.getAcknowledgeMode());
            System.out.println(session.getTransacted());
            System.out.println("接受队列消息，内容为：{}" + message);
            int i = 1 / 0;
            message.acknowledge();
        }catch (Exception e){
            session.recover();
        }
    }

//    @JmsListener(destination = "stringQueue", containerFactory = "jmsListenerContainerQueue")
//    public void receiveStringQueue(String msg) {
//        System.out.println("接收到消息...." + msg);
//    }


//    @JmsListener(destination = "stringListQueue", containerFactory = "jmsListenerContainerQueue")
//    public void receiveStringListQueue(List<String> list) {
//        System.out.println("接收到集合队列消息...." + list);
//    }
//
//
//    @JmsListener(destination = "objQueue", containerFactory = "jmsListenerContainerQueue")
//    public void receiveObjQueue(ObjectMessage objectMessage) throws Exception {
//        System.out.println("接收到对象队列消息...." + objectMessage.getObject());
//    }
//
//
//    @JmsListener(destination = "objListQueue", containerFactory = "jmsListenerContainerQueue")
//    public void receiveObjListQueue(ObjectMessage objectMessage) throws Exception {
//        System.out.println("接收到的对象队列消息..." + objectMessage.getObject());
//    }


}
