package com.wuzz.demo.flyweight;

import java.util.Random;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/24 19:05
 * @since 1.0
 **/
public class TrainTicket implements ITicket {
    private String from;
    private String to;
    private int price;

    public TrainTicket(String from, String to) {
        this.from = from;
        this.to = to;
    }


    public void showInfo(String bunk) {
        this.price = new Random().nextInt(500);
        System.out.println(String.format("%s->%s：%s价格：%s 元", this.from, this.to, bunk, this.price));
    }
}
