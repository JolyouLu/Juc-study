package com.juc.countDownLatchs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: LZJ
 * @Date: 2020/2/3 19:04
 * @Version 1.0
 */
public class CountDownLatch01 {

    private final static int threadCount = 100;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(threadCount); //初始化一个数量
        for (int i =0;i< threadCount;i++){
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    test(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown(); //每次执行完减一
                }
            });
        }
        countDownLatch.await(50, TimeUnit.MILLISECONDS);
        //等待50毫秒就增加执行如下代码，不管别的线程有没有跑完
        System.out.println("结束");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        Thread.sleep(50);
        System.out.println(threadNum);
        Thread.sleep(50);
    }
}
