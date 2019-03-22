package com.wuzz.demo.observer;

import java.lang.reflect.Method;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/22
 * Time: 11:46
 * Description 描述:一个对象状态改变给其他对象通知的问题，而且要考虑到易用和低耦合，保证高度的协作。
 */
public class Event {

    //事件源
    private Object source;
    //通知目标
    private Object target;
    //回调
    private Method callback;
    //触发
    private String trigger;
    //触发时间
    private long time;

    public Event(Object target, Method callback) {
        this.target = target;
        this.callback = callback;
    }
    // 重写一下toString 一边输出观察
    @Override
    public String toString() {
        return "Event{" +
                "\n\tsource=" + source + ",\n" +
                "\ttarget=" + target + ",\n" +
                "\tcallback=" + callback + ",\n" +
                "\ttrigger='" + trigger + '\'' + "\n" +
                '}';
    }

    public Object getSource() { return source;}

    public Object getTarget() {return target;}

    public void setTarget(Object target) {this.target = target;}

    public Method getCallback() {return callback;}

    public void setCallback(Method callback) {this.callback = callback;}

    public String getTrigger() {return trigger;}

    public long getTime() { return time;}

    Event setTime(long time) {
        this.time = time;
        return this;
    }

    Event setSource(Object source) {
        this.source = source;
        return this;
    }

    Event setTrigger(String trigger) {
        this.trigger = trigger;
        return this;
    }
}
