package com.wuzz.demo.retry;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/11/12 18:33
 * @since 1.0
 **/
public class CustomTask implements Runnable {

    private RetryServiceExecutor retryServiceExecutor;


    private int delay;


    private AtomicInteger retry = new AtomicInteger(0);

    public CustomTask(RetryServiceExecutor retryServiceExecutor) {
        this.retryServiceExecutor = retryServiceExecutor;
    }

    @Override
    public void run() {
        try {
            System.out.println(LocalDateTime.now());
            int i = 1 / 0;
        } catch (Exception e) {
            int retryCount = 1 << retry.incrementAndGet();// 0 1 2 3
            this.setDelay(this.delay + retryCount * 1000);// 0 2 6 14
            System.out.println(this.delay);
            retryServiceExecutor.enQueue(this);
        }

    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
