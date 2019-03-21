package com.wuzz.demo.singleton;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/20
 * Time: 15:37
 * Description 描述:懒汉式（线程安全）
 */
public class LazySingleton {

    private static LazySingleton singleton;

    private LazySingleton() {}

    public static synchronized LazySingleton getInstance() {
        if (singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }
}
