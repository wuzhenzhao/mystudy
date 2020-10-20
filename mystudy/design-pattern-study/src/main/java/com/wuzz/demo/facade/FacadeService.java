package com.wuzz.demo.facade;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/24 11:46
 * @since 1.0
 **/
public class FacadeService {

    private QualifyService qualifyService = new QualifyService();
    private PaymentService paymentService = new PaymentService();
    private ShippingService shippingService = new ShippingService();


    public void exchange(GiftInfo giftInfo){
        if(qualifyService.isAvailable(giftInfo)){
            if(paymentService.pay(giftInfo)){
                String shippingNo = shippingService.delivery(giftInfo);
                System.out.println("物流系统下单成功，物流单号是：" + shippingNo);
            }
        }
    }

}
