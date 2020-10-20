package com.wuzz.demo.bridge;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/24 17:19
 * @since 1.0
 **/
public class NomalMessage extends AbastractMessage {
    public NomalMessage(IMessage message) {
        super(message);
    }

    void sendMessage(String message, String toUser) {
        message = "【普通】" + message;
        super.sendMessage(message, toUser);
    }
}
