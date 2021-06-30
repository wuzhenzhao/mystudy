package com.wuzz.demo.concurrent.blockqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2021/6/29 15:17
 * @since 1.0
 **/
public class DelayQueueTest {

    private static DelayQueue delayQueue=new DelayQueue();
    public static void main(String[] args) {
        delayQueue.offer(new DelayQueueTask("1001",1000));
        delayQueue.offer(new DelayQueueTask("1002",4000));
        delayQueue.offer(new DelayQueueTask("1003",5000));
        delayQueue.offer(new DelayQueueTask("1004",2000));
        delayQueue.offer(new DelayQueueTask("1005",3000));
        delayQueue.offer(new DelayQueueTask("1006",8000));
        while (true){
            try {
                Delayed take = delayQueue.take();
                System.out.println(take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
