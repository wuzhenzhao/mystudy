package com.wuzz.demo.mediator;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/25 10:06
 * @since 1.0
 **/
public class User {
    private String name;
    private ChatRoom chatRoom;

    public User(String name, ChatRoom chatRoom) {
        this.name = name;
        this.chatRoom = chatRoom;
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String msg){
        this.chatRoom.showMsg(this,msg);
    }
}
