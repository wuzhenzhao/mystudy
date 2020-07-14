package com.wuzz.demo.qrcode;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/7/13 15:34
 * @since 1.0
 **/
public class Test {

    public static void main(String[] args) {
        long l = System.currentTimeMillis() - 1420041600000L;
        long a=l << 22
                |0
                |0
                |0;
        System.out.println(l);
        System.out.println(Long.toBinaryString(l));

        System.out.println(l<< 22);
        System.out.println(Long.toBinaryString(l<< 22));

        System.out.println(a);
        System.out.println(Long.toBinaryString(a));
        //732267165444472832
        //0
        //0
        //0
    }
}
