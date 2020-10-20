package com.wuzz.demo.facade;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/24 10:49
 * @since 1.0
 **/
public class ShippingService {

    public String delivery(GiftInfo giftInfo) {
        System.out.println(giftInfo.getName() + "进入物流系统");
        String shippingNo = "666";
        return shippingNo;
    }

}
