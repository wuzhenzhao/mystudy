package com.wuzz.demo.algorithm;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/7/25 14:18
 * @since 1.0
 **/
public class IntegerPowerOfTwo {

    /**
     * 如果一个整数是2的整数次幂，那么当它转化成二进制时，只有最高位是1，其他位都是0！
     * 2的整数次幂一旦减1，它的二进制数字就全部变成了1！
     *
     * 0和1按位与运算的结果是0，所以凡是2的整数次幂和它本身减1的结果进行与运算，结果都必定是0。
     * 反之，如果一个整数不是2的整数次幂，结果一定不是0！
     */
    public static boolean isPowerOf2(int num) {
        return (num & num - 1) == 0;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOf2(64));
        System.out.println(isPowerOf2(99));
    }
}
