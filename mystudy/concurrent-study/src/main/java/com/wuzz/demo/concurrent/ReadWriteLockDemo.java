package com.wuzz.demo.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/26 16:58
 * @since 1.0
 **/
public class ReadWriteLockDemo {
    static Map<String, Object> cacheMap = new HashMap<>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock read = rwl.readLock();//读锁
    static Lock write = rwl.writeLock();//写锁

    public static final Object get(String key) {
        System.out.println("开始读取数据");
        read.lock(); // 读锁
        try {
            return cacheMap.get(key);
        } finally {
            read.unlock();
        }
    }

    public static final Object put(String key, Object value) {
        write.lock();
        System.out.println("开始写数据");
        try {
            return cacheMap.put(key, value);
        } finally {
            write.unlock();
        }
    }
}
