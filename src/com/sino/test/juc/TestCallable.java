package com.sino.test.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Hover Kan
 * @create 2018-11-21 下午9:29
 *
 * 一、创建执行线程的方式三 : 实现Callable 接口。相较于实现 Runnable 接口的方式，方法可以有返回值，并且可以抛出异常
 *
 * 二、执行 Callable 方式，需要 FutureTask 实现类的支持，用于接受运算结果。FutureTask 是 Future 接口的实现类
 */
public class TestCallable {
    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();

        // 执行 Callable 方式，需要 FutureTask 实现类的支持，用于接受运算结果
        FutureTask<Integer> result = new FutureTask<>(td);

        new Thread(result).start();

        try {
            // 接受线程运算后的结果
            Integer integer = result.get(); // FutureTask 可用于 闭锁  可以将多个线程的结果合并进行运算
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}

class ThreadDemo implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 0; i <= 100; i++) {
            sum += i;
        }

        return sum;
    }
}