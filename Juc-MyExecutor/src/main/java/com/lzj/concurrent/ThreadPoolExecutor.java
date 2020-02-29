package com.lzj.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: LZJ
 * @Date: 2020/2/29 12:06
 * @Version 1.0
 */
public class ThreadPoolExecutor implements Executor {

    //核心容量
    private volatile int corePollSize;
    //最大容量
    private volatile int maximumPoolSize;
    //核心线程超时销毁
    private volatile long keepAliveTime;
    //描述是否需要超时销毁
    private volatile boolean allowCoreThreadTimeOut;
    //当前数量
    private final AtomicInteger ctl = new AtomicInteger(0);
    //队列
    private BlockingQueue<Runnable> workQueue;

    public ThreadPoolExecutor(int corePollSize, int maximumPoolSize, BlockingQueue<Runnable> workQueue) {
        this.corePollSize = corePollSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
    }

    public ThreadPoolExecutor(int corePollSize, int maximumPoolSize, long keepAliveTime, boolean allowCoreThreadTimeOut, BlockingQueue<Runnable> workQueue) {
        this.corePollSize = corePollSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        if (keepAliveTime>0){
            allowCoreThreadTimeOut= true;
        }
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
        this.workQueue = workQueue;
    }

    /**
     * 接收 task任务
     * @param command
     */
    @Override
    public void execute(Runnable command) {
        if (command == null){
            throw new NullPointerException();
        }
        int c = ctl.get();
        if (c < corePollSize){
            addWorker(command,true);
        }else if (workQueue.offer(command)){
            addWorker(null,false);
        }else {
            reject(command);
        }

    }

    //添加任务内容
    private void addWorker(Runnable task,boolean core) {
        if(core){//如果true，表示使用核心容量，计数加1
            ctl.incrementAndGet();
        }
        Worker worker = new Worker(task);
        worker.thread.start();
    }

    //拒绝策略 方法
    private void reject(Runnable command) {
        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler();
        rejectedExecutionHandler.rejectedExecution(command);
    }

    /**
     * 工作内容
     */
    class Worker extends ReentrantLock implements Runnable{
        private Runnable firstTask;
        private Thread thread;

        public Worker(Runnable firstTask) {
            this.firstTask = firstTask;
            thread = new Thread(this);
        }

        @Override
        public void run() {
            runWork(this);
        }

        //具体的执行任务方法
        private void runWork(Worker w) {
            try {
                w.lock();
                Runnable task = w.firstTask;
                if (task!=null || (task=getTask())!= null){
                    task.run();
                }
                task.run();
            }finally {
                w.unlock();
            }
        }

        private void processWorkerExit(Worker w){
            addWorker(null,false);
        }

        private Runnable getTask() {
            try {
                if (workQueue.isEmpty()){
                    return null;
                }
                //三目表达式 如果开启了超时 ？传入超时时间获取队列如果是超时了返回null ？ 否则直接返回队列元素
                Runnable r = allowCoreThreadTimeOut ? workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS): workQueue.take();
                if (r != null){
                    return r;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 拒绝策略
     */
    class RejectedExecutionHandler{
        public void rejectedExecution(Runnable command){
            throw new RejectedExecutionException("这个task"+command+"被拒绝");
        }
    }
}
