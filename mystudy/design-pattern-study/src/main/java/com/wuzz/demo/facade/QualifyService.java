package com.wuzz.demo.facade;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/24 10:48
 * @since 1.0
 **/
public class QualifyService {

    public boolean isAvailable(GiftInfo giftInfo) {
        System.out.println("校验" + giftInfo.getName() + "积分通过,库存通过。");
        return true;
    }
}
