package com.lzj.test;

import com.lzj.concurrent.ThreadPoolExecutor;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author: LZJ
 * @Date: 2020/2/29 12:42
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0,5,new LinkedBlockingDeque<>());
        for (int i=0;i<10;i++){
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("MyThreadPoolExecutor");
                }
            });
        }
    }
}
