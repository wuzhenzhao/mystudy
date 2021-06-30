package com.wuzz.demo.concurrent.vol;

import sun.misc.Contended;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2021/6/24 14:45
 * @since 1.0  缓存系统中是以缓存行（cache line）为单位存储的。缓存行是2的整数幂个连续字节，
 * 一般为32-256个字节。最常见的缓存行大小是64个字节。当多线程修改互相独立的变量时，
 * 如果这些变量共享同一个缓存行，就会无意中影响彼此的性能，这就是伪共享。
 *      缓存行 缓存行伪共享  对齐填充可以解决
 **/
public class CacheLineExample implements Runnable {
    public final static long ITERATIONS = 500L * 1000L * 100L;
    private int arrayIndex = 0;
    private static ValuePadding[] longs;

    public CacheLineExample(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        for (int i = 1; i < 10; i++) {
            System.gc();
            final long start = System.currentTimeMillis();
            runTest(i);
            System.out.println(i + " Threads, duration = " +
                    (System.currentTimeMillis() - start));
        }
    }

    private static void runTest(int NUM_THREADS) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        longs = new ValuePadding[NUM_THREADS];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new ValuePadding();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new CacheLineExample(i));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = 0L;
        }
    }

    // 该类是对齐填充的
    public final static class ValuePadding {
        protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
        protected long p9, p10, p11, p12, p13, p14;
        protected long p15;
    }

//    @Contended //实现对齐填充 生效要增加JVM参数 -XX:-RestrictContended
    public final static class ValueNoPadding {
        // protected long p1, p2, p3, p4, p5, p6, p7;
        //8字节
        protected volatile long value = 0L;
        // protected long p9, p10, p11, p12, p13, p14, p15;
    }
}
