package com.wuzz.demo.bridge;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/24 17:20
 * @since 1.0
 **/
public class UrgencyMessage extends AbastractMessage {
    public UrgencyMessage(IMessage message) {
        super(message);
    }

    void sendMessage(String message, String toUser) {
        message = "【加急】" + message;
        super.sendMessage(message, toUser);
    }

    //根据messageId可以监控
    public Object watch(String messageId) {
        return null;
    }
}
