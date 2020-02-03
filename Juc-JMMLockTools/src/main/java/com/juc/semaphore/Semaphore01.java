package com.juc.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @Author: LZJ
 * @Date: 2020/2/3 19:31
 * @Version 1.0
 */
public class Semaphore01 {
    //创建5个许可证
    private static Semaphore semaphore=new Semaphore(5);

    public static void main(String[] args) {
        //创建二十个线程同时进行秒杀
        for (int i=0;i<20;i++){
            final int j=i;
            new Thread(()->{
                try {
                    action(j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void action(int i) throws InterruptedException {
        //每次进入许可-1，最大5个许可
        semaphore.acquire();
        System.out.println(i+"在京东秒杀iphonex");
        System.out.println(i+"秒杀成功");
        semaphore.release();
        //每次结束许可+1，最大5个许可
    }
}
