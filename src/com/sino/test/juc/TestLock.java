package com.sino.test.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hover Kan
 * @create 2018-11-22 下午8:42
 *
 * 一、用于解决多线程安全问题的方式：
 *  synchronized 隐式锁
 *  1、同步代码块
 *
 *  2、同步方法
 *
 *  jdk 1.5 以后：
 *  3、同步锁 Lock
 *  注意：是一个显示锁，通过lock() 方法上锁，必须通过unlock() 方法进行释放锁【放在finally 里面 保证释放】
 *
 */

public class TestLock {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(ticket,"一号窗口").start();
        new Thread(ticket,"二号窗口").start();
        new Thread(ticket,"三号窗口").start();
    }

}

class Ticket implements Runnable{

    private int tick = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){

            lock.lock();//上锁
            try{

                if (tick > 0){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" 完成售票，余票为：" + --tick);
                }
            } finally {
                lock.unlock();//释放锁
            }
        }
    }
}
