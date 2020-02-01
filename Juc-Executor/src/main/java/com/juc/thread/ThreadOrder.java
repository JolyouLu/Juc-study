package com.juc.thread;

/**
 * @Author: LZJ
 * @Date: 2020/2/1 15:31
 * @Version 1.0
 */
//查看线程的顺序 结论是并行的
public class ThreadOrder {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            System.out.println("thread1");
        });
        Thread thread2 = new Thread(()->{
            System.out.println("thread2");
        });
        Thread thread3 = new Thread(()->{
            System.out.println("thread3");
        });
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
