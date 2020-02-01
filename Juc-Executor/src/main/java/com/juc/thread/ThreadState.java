package com.juc.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: LZJ
 * @Date: 2020/2/1 14:43
 * @Version 1.0
 */
//在cmd中 输入jsp 找到这个class对应的编号 jstack class编号，查看以下4个线程状态
public class ThreadState {

    public static void main(String[] args) {
        new Thread(new TimeWaiting (), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread").start();
        // 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
    }

    static class Waiting implements Runnable{
        // 该线程在Waiting.class实例上等待状态
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class TimeWaiting implements Runnable{
        // 该线程不断地进行睡眠
        public void run() {
            while (true){
                second(100);
            }
        }
    }

    static class Blocked implements Runnable{
        // 该线程在Blocked.class实例上加锁后，不会释放该锁
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    second(100);
                }
            }
        }
    }

    public static final void second(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
