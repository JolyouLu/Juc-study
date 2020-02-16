package com.juc.list;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: LZJ
 * @Date: 2020/2/16 14:38
 * @Version 1.0
 */
public class ArrayListExample {
    public static final List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        new Thread(){
            public void run(){
                for (int i = 0;i<1000;i++){
                    list.add(String.valueOf(i));
                }
            }
        }.start();

        new Thread(){
            public void run() {
                for(int j=1000;j<2000;j++){
                    list.add(String.valueOf(j));
                }
            }
        }.start();

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出
        for(int i=0;i<2000;i++){
            System.out.println("第："+i+"元素，值："+list.get(i));
        }
    }
}
