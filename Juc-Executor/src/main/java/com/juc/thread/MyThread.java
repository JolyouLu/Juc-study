package com.juc.thread;

/**
 * @Author: LZJ
 * @Date: 2020/2/1 14:09
 * @Version 1.0
 */
//继承Thread 重写run实现多线程
public class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println("继承Thread 重写run实现多线程");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
