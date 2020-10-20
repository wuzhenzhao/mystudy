package com.wuzz.demo.bridge;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/24 17:18
 * @since 1.0
 **/
public abstract class AbastractMessage {
    private IMessage message;

    public AbastractMessage(IMessage message) {
        this.message = message;
    }

    void sendMessage(String message, String toUser) {
        this.message.send(message, toUser);
    }
}
