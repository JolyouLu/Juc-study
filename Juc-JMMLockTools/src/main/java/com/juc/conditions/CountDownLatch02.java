package com.juc.conditions;

import java.nio.channels.NonWritableChannelException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: LZJ
 * @Date: 2020/2/3 19:14
 * @Version 1.0
 */
//使用 CountDownLatch 等待异步处理完成后我们才继续调用
public class CountDownLatch02 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CallableImpl callableImpl = new CallableImpl(countDownLatch);
        FutureTask<String> futureTask = new FutureTask<String>(callableImpl);
        new Thread(futureTask).start();
        if (!futureTask.isDone()){
            try {
                System.out.println("你知道我一直在等你嘛>>>>>>>>");
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(futureTask.get());
    }
}
