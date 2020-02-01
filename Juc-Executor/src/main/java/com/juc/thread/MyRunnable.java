package com.juc.thread;

/**
 * @Author: LZJ
 * @Date: 2020/2/1 14:10
 * @Version 1.0
 */
//实现Runnable接口实现run方法
public class MyRunnable implements Runnable{

    public void run() {
        System.out.println("实现Runnable接口实现run方法");
    }

    public static void main(String[] args) {
        //Runnable没有start方法他需要借助Thread启动
        new Thread(new MyRunnable()).start();
    }
}
