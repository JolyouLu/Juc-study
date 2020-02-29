package com.juc.singleton;

import javax.sound.midi.Soundbank;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @Author: LZJ
 * @Date: 2020/2/24 21:33
 * @Version 1.0
 * 饿汉模式 线程安全 消耗资源
 */
public class Singleton1 {

    private static Singleton1 singleton1 = new Singleton1();

    private Singleton1(){
    }

    public static Singleton1 getSingleton1(){
        return singleton1;
    }

    public static void main(String[] args) {
        for (int i = 0;i<100;i++){
            new Thread(()->{
                System.out.println(getSingleton1().hashCode());
            }).start();
        }
    }
}
