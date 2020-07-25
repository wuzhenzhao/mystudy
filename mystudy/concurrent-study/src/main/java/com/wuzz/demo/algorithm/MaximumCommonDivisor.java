package com.wuzz.demo.algorithm;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/7/25 13:59
 * @since 1.0
 **/
public class MaximumCommonDivisor {

    /**
     * 功能描述: <br>
     * <p>
     * 辗转相除法， 又名欧几里得算法（Euclidean algorithm），该算法的目的
     * 是求出两个正整数的最大公约数。它是已知最古老的算法， 其产生时间可追溯至公
     * 元前300年前。
     * 这条算法基于一个定理：两个正整数a和b（a>b），它们的最大公约数等于a除
     * 以b的余数c和b之间的最大公约数。
     * <p>
     * 当两个整数较大时，做a%b取模运算的性能会比较差。
     */
    public static int getGreatestCommonDivisorV2(int a, int b) {
        int big = a > b ? a : b;
        int small = a < b ? a : b;
        if (big % small == 0) {
            return small;
        }
        return getGreatestCommonDivisorV2(big % small, small);
    }

    /**
     * * 更相减损术，出自中国古代的《九章算术》，也是一种求最大公约数的算法。
     * * 它的原理更加简单：两个正整数a和b（a>b），它们的最大公约数等于a-b的差
     * * 值c和较小数b的最大公约数。例如10和25，25减10的差是15，那么10和25的最大
     * * 公约数，等同于10和15的最大公约数。
     * 当两数相差悬殊时，如计算10000和1的最大公约数，就要递归9999次！
     */
    public static int getGreatestCommonDivisorV3(int a, int b) {
        if (a == b) {
            return a;
        }
        int big = a > b ? a : b;
        int small = a < b ? a : b;

        return getGreatestCommonDivisorV2(big - small, small);
    }

    /**
     * 当a和b均为偶数时，gcd(a,b) = 2×gcd(a/2, b/2) = 2×gcd(a>>1,b>>1)。
     * 当a为偶数，b为奇数时，gcd(a,b) = gcd(a/2,b) = gcd(a>>1,b)。
     * 当a为奇数，b为偶数时，gcd(a,b) = gcd(a,b/2) = gcd(a,b>>1)。
     * 当a和b均为奇数时，先利用更相减损术运算一次，gcd(a,b) = gcd(b,a-b)，此
     * 时a-b必然是偶数，然后又可以继续进行移位运算。
     */
    public static int gcd(int a, int b) {
        if (a == b) {
            return a;
        }
        //a 是偶数 b 是偶数
        if ((a & 1) == 0 && (b & 1) == 0) {
            return gcd(a >> 1, b >> 1) << 1;
            //a是偶数 b是奇数
        } else if ((a & 1) == 0 && (b & 1) != 0) {
            return gcd(a >> 1, b);
            //a 是奇数，b是偶数
        } else if ((a & 1) != 0 && (b & 1) == 0) {
            return gcd(a, b >> 1);
        } else {//两个都是奇数
            int big = a > b ? a : b;
            int small = a < b ? a : b;
            return gcd(big - small, small);
        }
    }

    public static void main(String[] args) {
        System.out.println(getGreatestCommonDivisorV3(25, 5));
        System.out.println(getGreatestCommonDivisorV3(100, 80));
        System.out.println(getGreatestCommonDivisorV3(27, 14));
    }
}
