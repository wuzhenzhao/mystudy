package com.wuzz.demo.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/9 19:13
 * @since 1.0
 **/
public class NumObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {

        NumObservable myObserable = (NumObservable) o;
        System.out.println("Data has changed to " + myObserable.getData());

    }

}
