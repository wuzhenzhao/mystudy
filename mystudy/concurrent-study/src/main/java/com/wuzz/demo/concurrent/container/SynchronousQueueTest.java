package com.wuzz.demo.concurrent.container;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @description: 同步队列，但是队列长度为0，生产者放入队列的操作会被阻塞，
 * 直到消费者过来取，所以这个队列根本不需要空间存放元素；有点像一个独木桥，一次只能一人通过，还不能在桥上停留
 * @author: wuzhenzhao
 * @time 2020/8/26 19:22
 * @since 1.0
 **/
public class SynchronousQueueTest {

    // 特殊的 transferQueue ，队列为空的
    //任何添加东西都要直接丢给消费者的，不会往队列里加的
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        strs.put("aaa");//阻塞，等待消费者消费
//      strs.add("aaa"); // 报错
        System.out.println(strs.size());
    }
}
