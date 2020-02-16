package com.juc.aba;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: LZJ
 * @Date: 2020/2/15 15:56
 * @Version 1.0
 * 演示ABA问题
 */
public class AtomicIntegerABAExample {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        //线程1使用CAS操作把100修改成了101后把101又修改了100
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInteger.compareAndSet(100,101);
                atomicInteger.compareAndSet(101,100);
            }
        });
        //线程2使用CAS操作把100修改成101
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException r){}
                boolean b = atomicInteger.compareAndSet(100, 101);
                System.out.println(b);
                System.out.println(atomicInteger.get());
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
