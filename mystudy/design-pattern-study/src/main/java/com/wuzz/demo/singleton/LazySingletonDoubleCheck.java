package com.wuzz.demo.singleton;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/20
 * Time: 15:37
 * Description 描述:懒汉式（线程安全）的细粒度优化（双重锁机制）
 */
public class LazySingletonDoubleCheck {

    private static LazySingletonDoubleCheck instance;

    private LazySingletonDoubleCheck (){}

    /* @Author Wuzhenzhao
    * @Param []
    * @return com.wuzz.demo.singleton.LazySingletonDoubleCheck
    * @Date 16:04 2019/3/20
    * @Description          
    **/
    public static LazySingletonDoubleCheck getInstance(){

        if (instance == null){
            synchronized(LazySingletonDoubleCheck.class){
                if (instance == null)
                    instance = new LazySingletonDoubleCheck();
            }
        }
        return instance;
    }
}
