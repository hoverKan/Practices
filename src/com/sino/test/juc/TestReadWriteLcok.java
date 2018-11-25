package com.sino.test.juc;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Hover Kan
 * @create 2018-11-24 上午8:56
 * <p>
 * 1、ReadWriteLock : 读写锁
 * 写写/读写 需要"互斥"
 * 读读  不需要互斥
 */
public class TestReadWriteLcok {
    public static void main(String[] args) {
        ReadWriteLockDemo rw = new ReadWriteLockDemo();


        new Thread(new Runnable() {
            @Override
            public void run() {
                rw.set(new Random().nextInt(100));
            }
        }, "Write ").start();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    rw.get();
                }
            }).start();
        }

    }

}

class ReadWriteLockDemo {
    private int number;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void get() {
        lock.readLock().lock();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(Thread.currentThread().getName() + " : " + number);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void set(int number) {
        lock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + " : " + number);
            this.number = number;
        } finally {
            lock.writeLock().unlock();
        }
    }
}
