package com.juc.threadpool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @Author: LZJ
 * @Date: 2020/2/1 16:25
 * @Version 1.0
 */
//使用普通多线程往list插入随机数
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        //记录开始时间
        Long start = System.currentTimeMillis();
        final List<Integer> list = new ArrayList<>();
        final Random random = new Random();
        //循环add随机数入容器
        for (int i=0;i<10000;i++){
            //每次插入开一个线程
            Thread thread = new Thread(){
                public void run(){list.add(random.nextInt());}
            };
            thread.start();
            //需要等待所有进程结束才能结束main进程不然会出现数据长度对不上
            thread.join();
        }
        System.out.println("耗时:"+(System.currentTimeMillis()-start));
        System.out.println(list.size());
    }
}
