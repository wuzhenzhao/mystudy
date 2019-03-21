package com.wuzz.demo.singleton;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/20
 * Time: 16:04
 * Description 描述:静态内部类（懒加载，线程安全）
 */
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton() {}
    //内部类在外部类调用的时候才会被初始化
    // 内部类一定要在方法调用之前初始化
    private static class SingletonInstance {
        private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }
    // static 使单例空间共享
    // final使得方法不能被重写重载
    public static final StaticInnerClassSingleton getInstance() {
        return SingletonInstance.instance;
    }
}
