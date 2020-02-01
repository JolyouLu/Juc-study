package com.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: LZJ
 * @Date: 2020/2/1 17:09
 * @Version 1.0
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println("CachedThreadPool 线程池");
            }
        });
        executorService.shutdown();
    }
}
