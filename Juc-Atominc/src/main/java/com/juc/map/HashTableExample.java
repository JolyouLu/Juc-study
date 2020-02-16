package com.juc.map;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: LZJ
 * @Date: 2020/2/16 14:05
 * @Version 1.0
 */
public class HashTableExample {
    public static final Map<String, String> map = new Hashtable<>();

    public static void main(String[] args) {
        new Thread(){
            public void run(){
                for (int i = 0;i<1000;i++){
                    map.put(String.valueOf(i),String.valueOf(i));
                }
            }
        }.start();

        new Thread(){
            public void run() {
                for(int j=1000;j<2000;j++){
                    map.put(String.valueOf(j), String.valueOf(j));
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
