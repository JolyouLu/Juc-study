package com.lzj.concurrent;

/**
 * @Author: LZJ
 * @Date: 2020/2/29 12:04
 * @Version 1.0
 */
public interface Executor {

    void execute(Runnable task);

}
