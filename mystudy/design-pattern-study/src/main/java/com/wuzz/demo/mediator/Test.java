package com.wuzz.demo.mediator;

/**
 * @description:中介者模式
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/25 10:06
 * @since 1.0
 **/
public class Test {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();

        User tom = new User("Tom",chatRoom);
        User jerry = new User("Jerry",chatRoom);

        tom.sendMessage("Hi! I am Tom.");
        jerry.sendMessage("Hello! My name is Jerry.");
    }
}
