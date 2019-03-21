package com.wuzz.demo.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/20
 * Time: 14:53
 * Description:Spring中的做法，就是用这种注册式单例
 */
public class BeanFactory {

    private BeanFactory(){}

    //线程安全的容器,饿汉式保证容器对象本身为单例
    private static Map<String,Object> ioc = new ConcurrentHashMap<String,Object>();

    public static Object getBean(String className){

        if(!ioc.containsKey(className)){
            Object obj = null;
            try {//用反射的方式创建对象(因为已经构造函数私有化),并登记到容器中
                obj = Class.forName(className).newInstance();
                ioc.put(className,obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }else{//从容器中获取管理的单例对象并返回
            return ioc.get(className);
        }
    }
}
