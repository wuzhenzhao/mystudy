package com.wuzz.demo.concurrent;

import java.util.concurrent.*;

/**
 * @description: callable 测试demo
 * @author: wuzhenzhao
 * @time 2020/4/26 11:14
 * @since 1.0
 **/
public class CallableDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "Hello Callable....";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<String> submit = executorService.submit(new CallableDemo());
        String s = submit.get();
        System.out.println(s);
        executorService.shutdown();
    }
}
