package com.wuzz.demo.bridge;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/24 17:16
 * @since 1.0
 **/
public interface IMessage {
    //发送消息的内容和接收人
    void send(String message,String toUser);
}
