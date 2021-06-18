package com.wuzz.demo.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2021/5/17 15:42
 * @since 1.0
 **/
public class Scheduler {

    private ExecutorService pool = Executors.newScheduledThreadPool(8);

    public Scheduler(Demo demo) {
        pool.execute(demo);
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        new Scheduler(demo);
    }
}
