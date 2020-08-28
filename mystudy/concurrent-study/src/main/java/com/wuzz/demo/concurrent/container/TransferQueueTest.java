package com.wuzz.demo.concurrent.container;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/8/26 19:37
 * @since 1.0
 **/
public class TransferQueueTest {
    //TransferQueue.class，转移队列接口，生产者要等消费者消费的队列，生产者尝试把元素直接转移给消费者
    public static void main(String[] args) throws InterruptedException {

        //转移队列的链表实现，它比SynchronousQueue更快，transfer 方法有客户端准备消费，
        // 直接把消息直接传递给消费者，不放到队列里，没有消费者线程的话该线程会阻塞。
        // 但是可以调用 add put 王队列里丢，队列还是有容量的。
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        strs.transfer("aaa");
//      new Thread(()->{
//          try {
//              System.out.println(strs.take());
//          } catch (InterruptedException e) {
//              e.printStackTrace();
//          }
//      }).start();
    }
}
