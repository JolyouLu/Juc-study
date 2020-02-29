package com.juc.schedule;

import java.util.Date;

/**
 * @Author: LZJ
 * @Date: 2020/2/24 21:11
 * @Version 1.0
 */
public class Monitor implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start. Time = "+new Date());
        System.out.println("java.version : "+System.getProperty("java.version"));
        System.out.println("java.class.pth : "+System.getProperty("java.class.pth"));
        System.out.println("user.dir : "+System.getProperty("user.dir"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" End. Time = "+new Date());
        System.out.println("====================================");
    }
}
