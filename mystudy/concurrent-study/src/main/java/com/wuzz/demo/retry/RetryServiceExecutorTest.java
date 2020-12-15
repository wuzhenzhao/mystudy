package com.wuzz.demo.retry;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/11/12 18:40
 * @since 1.0
 **/
public class RetryServiceExecutorTest {

    public static void main(String[] args) {
        RetryServiceExecutor executor = new RetryServiceExecutor();
        CustomTask task = new CustomTask(executor);
        executor.enQueue(task);
        executor.start();
    }
}
