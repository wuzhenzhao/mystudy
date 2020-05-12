package com.wuzz.demo.observer.jdk;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/9 19:14
 * @since 1.0
 **/
public class SingleTest {

    public static void main(String[] args) {

        NumObservable number = new NumObservable();
        number.addObserver(new NumObserver());
        number.setData(1);
        number.setData(2);
        number.setData(3);

    }
}
