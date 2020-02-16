package com.juc.aba;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author: LZJ
 * @Date: 2020/2/15 16:03
 * @Version 1.0
 */
public class AtomicStampedABAExample {
    private static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100,0);

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){}
                atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = atomicStampedReference.getStamp();
                try {
                    TimeUnit.SECONDS.sleep(2);
                }catch (InterruptedException r){}
                boolean c3 = atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 1);
                System.out.println(c3); // false
            }
        });

        thread1.start();
        thread2.start();
    }
}
