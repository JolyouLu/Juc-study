package com.juc.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: LZJ
 * @Date: 2020/2/1 16:23
 * @Version 1.0
 */
//使用线程池往list插入随机数
public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        //记录开始时间
        Long start = System.currentTimeMillis();
        final List<Integer> list = new ArrayList<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Random random = new Random();
        //循环add随机数入容器
        for (int i=0;i<10000;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            });
        }
        //停止接收新的任务，并且等待未完成任务完成后关闭线程池
        executorService.shutdown();
        //如果线程超时一天还没关闭，输出线程池没有关闭，直到关闭为止后执行后面代码
        while (!executorService.awaitTermination(1, TimeUnit.DAYS)) {
            System.out.println("线程池没有关闭");
        }
        System.out.println("线程池已经关闭");
        System.out.println("耗时:"+(System.currentTimeMillis()-start));
        System.out.println(list.size());
    }
}
