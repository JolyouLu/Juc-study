package com.juc.scheduledExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: LZJ
 * @Date: 2020/2/16 18:55
 * @Version 1.0
 */
public class ScheduledThreadPoolExecutorTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.schedule(()->{
            System.out.println("延时任务：10秒后运行一次");
        },10, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(()->{
            System.out.println("延时任务：3秒后运行一次");
        },3, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println("定时任务：3秒跑一次");
        },0,3,TimeUnit.SECONDS);
    }
}
