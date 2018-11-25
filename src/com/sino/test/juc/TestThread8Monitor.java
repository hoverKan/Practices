package com.sino.test.juc;

/**
 * @author Hover Kan
 * @create 2018-11-24 上午10:00
 * <p>
 * 题目：判断打印的 "one" or "two" ?
 * <p>
 * 1、两个普通synchronized方法，两个线程 标准打印，打印？ // one  two
 * 2、新增 Thread.sleep 给 getOne(),打印？ // one two
 * 3、新增普通方法 【不加synchronized】,打印？ // Three one two
 * 4、两个普通同步方法，两个NumberWrite对象，打印？ // Two One
 * 5、修改getOne() 为静态同步方法，打印？ // two one
 * 6、修改两个方法均为静态同步方法，一个NumberWrite对象 ，打印？ // one two
 * 7、一个静态同步方法，一个非静态同步方法，两个NumberWrite对象？//  two one
 * 8、两个静态同步方法，两个NumberWrite对象？ // one two
 *
 * 线程八锁的关键：
 *   1、非静态的锁默认为 this ，静态方法的锁为对应的 Class 实例
 *   2、某一时刻内，只能有一个线程持有锁，无论几个方法。
 *
 */
public class TestThread8Monitor {
    public static void main(String[] args) {
        NumberWrite nw = new NumberWrite();
        NumberWrite nw1 = new NumberWrite();

        new Thread(new Runnable() {
            @Override
            public void run() {
                nw.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // nw.getTwo();
                nw1.getTwo();
            }
        }).start();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                nw.getThree();
            }
        }).start();*/
    }
}

class NumberWrite {
    public static synchronized void getOne() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("One");
    }

    public static synchronized void getTwo() {
        System.out.println("Two");
    }

    public void getThree(){
        System.out.println("Three");
    }
}

