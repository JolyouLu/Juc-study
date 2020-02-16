package com.juc.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LZJ
 * @Date: 2020/2/16 13:18
 * @Version 1.0
 */
public class HashMapExample {
    public static final Map<String,String> map = new HashMap<>();

    public static void main(String[] args) {
        //线程1 插入0-1000
        new Thread(){
            public void run(){
                for (int i=0;i<1000;i++){
                    map.put(String.valueOf(i),String.valueOf(i));
                }
            }
        }.start();

        //线程2 插入1000-2000
        new Thread(){
            public void run(){
                for (int j=1000;j<2000;j++){
                    map.put(String.valueOf(j),String.valueOf(j));
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
            System.out.println("第："+i+"元素，值："+map.get(String.valueOf(i)));
        }
    }
}
