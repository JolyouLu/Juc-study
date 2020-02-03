package com.juc.synchronizeds;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @Author: LZJ
 * @Date: 2020/2/3 17:26
 * @Version 1.0
 */
public class MyReentrantLock implements Runnable{
    public static ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (reentrantLock.tryLock(5, TimeUnit.SECONDS)) { //让线程等待5秒看能不能那到锁 拿不到就取else
                Thread.sleep(6000); //模拟进来的线程都要执行6秒才释放锁
                System.out.println("获取");
            } else {
                System.out.println("获取失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(reentrantLock.isHeldByCurrentThread()) {// 不加这个条件会报错 getHoldCount()方法来检查当前线程是否拥有该锁
                reentrantLock.unlock(); //如果没有锁 解锁会报错
            }
        }
    }

    public static void main(String[] args) {
        MyReentrantLock myReentrantLock = new MyReentrantLock();
        Thread thread1 =new Thread(myReentrantLock);
        thread1.start();
        Thread thread2 = new Thread(myReentrantLock);
        thread2.start();
        thread1.interrupt();

    }
}
