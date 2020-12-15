package com.wuzz.demo.retry;

import java.util.concurrent.*;

/**
 * @description: 重试执行器
 * @author: wuzhenzhao
 * @time 2020/11/12 18:31
 * @since 1.0
 **/
public class RetryServiceExecutor extends Thread {


    LinkedBlockingQueue<CustomTask> queue = new LinkedBlockingQueue<CustomTask>();

    ScheduledExecutorService service = Executors.newScheduledThreadPool(5);


    @Override
    public void run() {
        CyclicBarrier cyclicBarrier =new CyclicBarrier(10);
//        CyclicBarrier(int parties)
//        创建一个新的 CyclicBarrier ，当给定数量的线程（线程）等待它时，它将跳闸，并且当屏障跳闸时不执行预定义的动作。
//        CyclicBarrier(int parties, Runnable barrierAction)
//        创建一个新的 CyclicBarrier ，当给定数量的线程（线程）等待时，它将跳闸，当屏障跳闸时执行给定的屏障动作，由最后一个进入屏障的线程执行。
//        int await() 等待所有 parties已经在这个障碍上调用了 await 。
//        int await(long timeout, TimeUnit unit) 等待所有 parties已经在此屏障上调用 await ，或指定的等待时间过去。
//        int getNumberWaiting() 返回目前正在等待障碍的各方的数量。
//        int getParties() 返回旅行这个障碍所需的parties数量。
//        boolean isBroken() 查询这个障碍是否处于破碎状态。
//        void reset() 将屏障重置为初始状态。

        while (true) {
            CustomTask customTask = null;
            try {
                customTask = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            service.schedule(customTask, customTask.getDelay(), TimeUnit.MILLISECONDS);
        }

    }

    public void enQueue(CustomTask task){
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
