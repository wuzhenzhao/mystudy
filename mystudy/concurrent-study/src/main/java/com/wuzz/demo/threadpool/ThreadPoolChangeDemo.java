package com.wuzz.demo.threadpool;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2021/7/5 15:34
 * @since 1.0
 **/
public class ThreadPoolChangeDemo {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
            6, 60, TimeUnit.SECONDS,
            new ResizeLinkedBlockingQueue<Runnable>(30));

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPoolStatus("before");
        threadPoolExecutor.setCorePoolSize(4);
        threadPoolExecutor.setMaximumPoolSize(10);
        ResizeLinkedBlockingQueue<Runnable> queue = (ResizeLinkedBlockingQueue) threadPoolExecutor.getQueue();
        queue.setCapacity(100);
        threadPoolStatus("after");
    }

    private static void threadPoolStatus(String name) {
        ResizeLinkedBlockingQueue<Runnable> queue = (ResizeLinkedBlockingQueue) threadPoolExecutor.getQueue();
        System.out.println("核心线程数：" + threadPoolExecutor.getCorePoolSize() + "," +
                "最大线程数：" + threadPoolExecutor.getMaximumPoolSize() + "," +
                "队列长度：" + (queue.size() + queue.remainingCapacity()));
    }
}
