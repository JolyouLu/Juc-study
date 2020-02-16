package com.juc.scenes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: LZJ
 * @Date: 2020/2/14 16:37
 * @Version 1.0
 * 订单生产演示
 */
public class OrdersOrdersInteger {
    //原子性操作类
    static AtomicInteger count = new AtomicInteger(0);
    //普通int类
//    static Integer count = new Integer(0);

    public String getOrdersNo(){
        SimpleDateFormat data = new SimpleDateFormat("YYYYMMDDHHMMSS");
        //保证原子性 不会有重复订单id
        return data.format(new Date())+count.incrementAndGet();
        //无法保证原子性递增 出现生产重复订单id
//        return data.format(new Date())+count++;
    }

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(1);
        ExecutorService exeuctor = Executors.newFixedThreadPool(10);
        final OrdersOrdersInteger orderServer=new OrdersOrdersInteger();
        for (int i =0;i<10;i++){
            exeuctor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(orderServer.getOrdersNo());;
                }
            });
        }
        latch.countDown();
        exeuctor.shutdown();
    }
}
