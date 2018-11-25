package com.sino.test;

/**
 * @author Hover Kan
 * @create 2018-08-17 下午5:25
 */
public class Monkey {
    public static void main(String[] args) {
        int sum = 0;
        sum = count(8);
        System.out.println(sum);
    }

    //  到了第十天桃子还剩下一个，猴子每天吃剩下桃子的一半并再多吃一个。计算猴子吃的桃的总数？
    public static int count(int day) {
        if (day == 10)
            return 1;
        else
            return 2 * count(day + 1) + 2;
    }
}
