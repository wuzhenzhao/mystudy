package com.wuzz.demo.observer.jdk;

import java.util.Observable;

/**
 * @description: 被观察者
 * @author: wuzhenzhao
 * @time 2020/5/9 19:12
 * @since 1.0
 **/
public class NumObservable extends Observable {

    private int data = 0;

    public int getData() {
        return data;
    }

    public void setData(int i) {
        data = i;
        setChanged();
        notifyObservers();
    }
}
