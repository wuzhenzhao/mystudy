package com.wuzz.demo.facade;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/24 10:49
 * @since 1.0
 **/
public class PaymentService {

    public boolean pay(GiftInfo giftInfo) {
        System.out.println("扣减" + giftInfo.getName() + " 积分成功");
        return true;
    }

}
