package com.juc.volatiles;

/**
 * @Author: LZJ
 * @Date: 2020/2/3 14:40
 * @Version 1.0
 */
public class MyVolatile {
    //加上与去处volatile分别运行程序查看结果
    volatile boolean stop = false;

    public void shutDown(){
        stop = true;
    }

    public void doWork(){
        System.out.println("机器工作了");
        while (!stop){
        }
        System.out.println("机器停止了");
    }

    public static void main(String[] args) throws InterruptedException {
        MyVolatile myVolatile = new MyVolatile();
        new Thread(()->{
            myVolatile.doWork();
        }).start();
        //休息1秒 保证doWork先启动
        Thread.sleep(1000);
        new Thread(()->{
            myVolatile.shutDown();
        }).start();
    }
}
