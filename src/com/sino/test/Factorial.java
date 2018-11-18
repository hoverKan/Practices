package com.sino.test;

import java.util.Scanner;

/**
 * @author Hover Kan
 * @create 2018-08-17 下午5:05
 * 实现键盘输入一个整数n，在控制台输出n的阶乘
 */
public class Factorial {
    public static void main(String[] args) {
        System.out.println("请输入n： ");
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        System.out.println("n的阶乘为："+factorial(n));
    }
    //实现n的阶乘
    public static int factorial(int n){
        int result =1 ;
        if (n != 1){
           result =  n * factorial(n-1);
        }

        return result;
    }
}
