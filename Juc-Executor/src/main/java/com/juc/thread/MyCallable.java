package com.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: LZJ
 * @Date: 2020/2/1 14:09
 * @Version 1.0
 */
//实现Callable 重写call方法 可带参数返回
public class MyCallable implements Callable<String> {

    public String call() throws Exception {
        return "实现Callable 重写call方法 可带参数返回";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new MyCallable());
        new Thread(task).start();
        System.out.println(task.get());
    }
}
