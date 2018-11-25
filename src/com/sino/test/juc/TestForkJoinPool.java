package com.sino.test.juc;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author Hover Kan
 * @create 2018-11-25 上午8:40
 */
public class TestForkJoinPool {
    public static void main(String[] args) {
        Instant now = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0L, 500000000000L);

        Long sum = pool.invoke(task);

        System.out.println(sum);
                                                                        //100000000L--10000000000L--50000000000L--500000000000L
        Instant now1 = Instant.now();
        System.out.println("耗费时间：" + Duration.between(now, now1).toMillis());// 111 --4639 --21744--
    }

    @Test
    public void test01(){
        Instant now = Instant.now();

        long sum = 0L;
        for (long i = 0; i < 500000000000L; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant now1 = Instant.now();
        System.out.println("耗费时间：" + Duration.between(now, now1).toMillis());// 72 --3893  --17766
    }

    // Java8 新特性
    @Test
    public void test02(){
        Instant now = Instant.now();

        Long sum = LongStream.rangeClosed(0L, 500000000000L)
                            .parallel()
                            .reduce(0L, Long::sum);

        Instant now1 = Instant.now();
        System.out.println("耗费时间："+Duration.between(now,now1).toMillis());// 173 -- 3369 --14791
    }
}

class ForkJoinSumCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;

    private static final long THRESHOLD = 10000L;   // 临界值

    public ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0L;

            for (long i = start; i <= end; i++) {
                sum += i;
            }

            return sum;
        } else {
            long middle = (start + end)/2;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
            left.fork(); // 进行拆分，同时压入线程队列

            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
