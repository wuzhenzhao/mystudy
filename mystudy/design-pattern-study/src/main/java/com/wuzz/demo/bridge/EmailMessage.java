package com.wuzz.demo.bridge;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/24 17:17
 * @since 1.0
 **/
public class EmailMessage implements IMessage {
    public void send(String message, String toUser) {
        System.out.println("使用邮件消息发送" + message + "给" + toUser);
    }
}
