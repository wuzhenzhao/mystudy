package com.wuzz.demo;

import com.wuzz.demo.lock.DistributedlockUtils;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/12 15:25
 * @since 1.0
 **/
public class UnitTest extends Thread {

    @Override
    public void run() {
        while (true) {
            DistributedlockUtils distributedLock = new DistributedlockUtils();
            String rs = distributedLock.acquireLock(2000, 5000);
            if (rs != null) {
                System.out.println(Thread.currentThread().getName() + "-> 成功获得锁:" + rs);
                try {
                    Thread.sleep(1000);
                    distributedLock.releaseLock(rs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        UnitTest unitTest = new UnitTest();
        for (int i = 0; i < 10; i++) {
            new Thread(unitTest, "tName:" + i).start();
        }
    }
}
