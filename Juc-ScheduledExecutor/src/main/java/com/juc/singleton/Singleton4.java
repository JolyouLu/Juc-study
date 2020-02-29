package com.juc.singleton;

import com.juc.util.TlUtil;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: LZJ
 * @Date: 2020/2/24 22:18
 * @Version 1.0
 * 静态内部类实现线程安全的单例
 */
public class Singleton4 {

    private Singleton4(){
    }
    private static class SingletonInner{
        private static final Singleton4 SINGLETON_4 = new Singleton4();
    }

    public static Singleton4 getSingleton4(){
        return SingletonInner.SINGLETON_4;
    }

    public static void main(String[] args) {
        final Set set;
        set = new CopyOnWriteArraySet();
        for (int i = 0;i<100;i++){
            new Thread(()->{
                set.add(getSingleton4().hashCode());
            }).start();
        }
        System.out.println(set.size());

    }

}
