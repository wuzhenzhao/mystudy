package com.wuzz.demo.concurrent.blockqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2021/6/29 15:14
 * @since 1.0
 **/
public class DelayQueueTask implements Delayed {


    private String orderId;
    private long start=System.currentTimeMillis();
    private long time;

    public DelayQueueTask(String orderId, long time) {
        this.orderId = orderId;
        this.time = time;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((start+time)-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(this.getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "DelayQueueTask{" +
                "orderId='" + orderId + '\'' +
                ", start=" + start +
                ", time=" + time +
                '}';
    }
}
