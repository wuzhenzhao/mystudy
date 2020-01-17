package com.wuzz.demo;

import java.util.Scanner;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/12
 * Time: 17:52
 * Description 描述:
 */
public class Test {
    // 斐波那契数列
    //    public static long fibonacci(long number) {
//        if ((number == 0) || (number == 1))
//            return number;
//        else
//            return fibonacci(number - 1) + fibonacci(number - 2);
//    }
//    public static void main(String[] args) {
//        for (int counter = 0; counter <= 10; counter++){
//            System.out.printf("Fibonacci of %d is: %d\n",
//                    counter, fibonacci(counter));
//        }
//    }
    //杨辉三角
    public static void func(int n) {
        if (n < 0) return;
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for(int k=n-i;k>0;k--){
                System.out.print("*");
            }
            if (i == 0) {
                a[i][0] = 1;
                System.out.print(1);
            }
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == n - 1) {
                    a[i][j] = 1;
                } else {
                    a[i][j] = a[i - 1][j - 1] + a[i - 1][j];
                }
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

//    public static void main(String[] args) {
//        System.out.println("Enter the number:");
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        func(n);
//    }

//    public static void main(String[] args) {
//        System.out.println(5/2);
//    }
}
