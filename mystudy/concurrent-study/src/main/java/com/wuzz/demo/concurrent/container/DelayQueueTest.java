package com.wuzz.demo.concurrent.container;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @description: 阻塞队列，无界队列，并且元素是Delay的子类，保证元素在达到一定时间后才可以取得到
 * @author: wuzhenzhao
 * @time 2020/8/26 16:32
 * @since 1.0
 **/
public class DelayQueueTest {
    // 执行定时任务
    static BlockingQueue<MyTask> tasks = new DelayQueue<>();

    static class MyTask implements Delayed {

        long runningTime;

        MyTask(Long rt) {

            this.runningTime = rt;
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
                return -1;
            if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS))
                return 1;
            else
                return 0;
        }

        //返回与此对象相关的剩余延迟时间，以给定的时间单位表示。
        //零或负值指示延迟时间已经用尽
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public String toString() {
            return "" + runningTime;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();

        MyTask t1 = new MyTask(now + 1000);
//        MyTask t2 = new MyTask(now + 2000);
//        MyTask t3 = new MyTask(now + 1500);
//        MyTask t4 = new MyTask(now + 2500);
//        MyTask t5 = new MyTask(now + 500);

        tasks.put(t1);
//        tasks.put(t2);
//        tasks.put(t3);
//        tasks.put(t4);
//        tasks.put(t5);

        System.out.println(tasks);

        for (int i = 0; i < 1; i++) {
            System.out.println(tasks.take());
        }

    }
}
