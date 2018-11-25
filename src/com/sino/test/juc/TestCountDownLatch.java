package com.sino.test.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author Hover Kan
 * @create 2018-11-21 下午9:10
 *
 * CountDownLatch : 闭锁，在完成某些运算时，只有其他线程的运算全部结束，当前运算才继续执行
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(5);
        LatchDemo ld = new LatchDemo(latch);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            new Thread(ld).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("耗费的时间为："+(end - start));
    }
}


class LatchDemo implements Runnable{

    private CountDownLatch latch;

    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {

        synchronized (this){
            try{

                for (int i = 0; i < 50000; i++) {
                    if (i % 2 == 0){
                        System.out.println(i);
                    }
                }
            } finally {
                latch.countDown();
            }
        }
    }
}