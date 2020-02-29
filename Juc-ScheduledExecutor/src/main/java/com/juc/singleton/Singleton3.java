package com.juc.singleton;

import com.juc.util.TlUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: LZJ
 * @Date: 2020/2/24 22:13
 * @Version 1.0
 * 使用枚举方式 确保线程安全
 */
public class Singleton3 {

    public Singleton3() {
    }
    private enum SingletonEnum{
        SINGLETON_ENUM;
        private Singleton3 singleton3;
        //jvm保证这个方法绝对的调用一次
        SingletonEnum(){singleton3 = new Singleton3();}
        public Singleton3 getSingleton3(){return singleton3;}
    }

    public static Singleton3 getSingleton3(){
        return SingletonEnum.SINGLETON_ENUM.getSingleton3();
    }

    public static void main(String[] args) {
        final Set set;
        set = new CopyOnWriteArraySet();
        for (int i = 0;i<100;i++){
            new Thread(()->{
                set.add(getSingleton3().hashCode());
            }).start();
        }
        System.out.println(set.size());
    }
}
