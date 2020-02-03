package com.juc.synchronizeds;

/**
 * @Author: LZJ
 * @Date: 2020/2/3 16:15
 * @Version 1.0
 */
public class MySynchronized {
    public static void main(String[] args) {
        //使用方法1 对象锁
        synchronized (MySynchronized.class){
        }
        //调用代码块
        m();
    }
    //使用方法2 定义静态代码块
    public static synchronized void m(){
    }
}
