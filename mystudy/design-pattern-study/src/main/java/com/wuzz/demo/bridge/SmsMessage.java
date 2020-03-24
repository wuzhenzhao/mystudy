package com.wuzz.demo.bridge;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/24 17:18
 * @since 1.0
 **/
public class SmsMessage implements IMessage {
    public void send(String message, String toUser) {
        System.out.println("使用短信消息发送" + message + "给" + toUser);
    }
}