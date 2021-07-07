package com.wuzz.demo.threadpool;

import java.util.concurrent.*;

/**
 * @description: Fixed线程池demo
 * @author: wuzhenzhao
 * @time 2020/4/27 11:02
 * @since 1.0
 **/
public class FixedThreadPoolDemo {
    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        //创建一个5个线程的线程池
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 6; i++) {//往池子里仍了6个任务
            service.execute(() -> {//睡500毫秒后打印线程名
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        //java.util.concurrent.ThreadPoolExecutor@119d7047[Running, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
        System.out.println(service);
        service.shutdown();//关闭线程池 等待任务都执行完再关闭
        System.out.println(service.isTerminated());//false 任务是否都执行完
        System.out.println(service.isShutdown());//true 是不是关闭？ 关闭了不代表任务执行完。
        //java.util.concurrent.ThreadPoolExecutor@119d7047[Shutting down, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
        System.out.println(service);
        try {
            TimeUnit.SECONDS.sleep(5);//睡5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(service.isTerminated());//true
        System.out.println(service.isShutdown());//true
        //java.util.concurrent.ThreadPoolExecutor@119d7047[Terminated, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 6]
        System.out.println(service);

    }
}
