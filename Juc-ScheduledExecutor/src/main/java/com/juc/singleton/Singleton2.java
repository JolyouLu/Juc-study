package com.juc.singleton;

import com.juc.util.TlUtil;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: LZJ
 * @Date: 2020/2/24 21:47
 * @Version 1.0
 * 懒汉模式 线程安全 使用双重检测
 */
public class Singleton2 {

    private static volatile Singleton2 singleton2 = null;

    private Singleton2(){
    }

    public static Singleton2 getSingleton2(){
        if (null == singleton2){
            synchronized (Singleton2.class){
                if (null == singleton2){
                    singleton2 = new Singleton2();
                }
            }
        }
        return singleton2;
    }

    public static void main(String[] args) throws InterruptedException {
        final Set set;
        set = new CopyOnWriteArraySet();
        for (int i = 0;i<100;i++){
            new Thread(()->{
                set.add(getSingleton2().hashCode());
            }).start();
        }
        System.out.println(set.size());
    }
}
