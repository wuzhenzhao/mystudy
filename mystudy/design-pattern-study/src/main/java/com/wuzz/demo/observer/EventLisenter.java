package com.wuzz.demo.observer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/22
 * Time: 11:47
 * Description 描述:
 */
public class EventLisenter {

    //Map相当于是一个注册器
    protected Map<Enum,Event> events = new HashMap<Enum,Event>();

    public void addLisenter(Enum eventType, Object target, Method callback){
        //注册事件
        //用反射调用这个方法
        events.put(eventType,new Event(target,callback));
    }

    private void trigger(Event e){
        e.setSource(this);//设置事件源
        e.setTime(System.currentTimeMillis());

        try {
            //反射调用该方法
            e.getCallback().invoke(e.getTarget(),e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    protected void trigger(Enum call){
        // 看是否注册了该事件
        if(!this.events.containsKey(call)){ return ;}
        //注册则触发
        trigger(this.events.get(call).setTrigger(call.toString()));
    }
}
