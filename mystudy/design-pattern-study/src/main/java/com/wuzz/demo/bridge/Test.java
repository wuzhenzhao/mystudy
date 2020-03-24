package com.wuzz.demo.bridge;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/24 17:20
 * @since 1.0
 **/
public class Test {

    public static void main(String[] args) {
        IMessage message = new SmsMessage();
        AbastractMessage abastractMessage = new NomalMessage(message);
        abastractMessage.sendMessage("加班申请", "王总");

        message = new EmailMessage();
        abastractMessage = new UrgencyMessage(message);
        abastractMessage.sendMessage("加班申请", "王总");
    }

}
