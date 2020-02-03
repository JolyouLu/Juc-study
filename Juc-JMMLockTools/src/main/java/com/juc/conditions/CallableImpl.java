package com.juc.conditions;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class CallableImpl implements Callable  {
  private CountDownLatch latch;
  public CallableImpl(CountDownLatch latch) {
    this.latch=latch;
  }
  public String doSomeThing(){
    latch.countDown();
    return "一分钟就干完了";
  }


  @Override
  public Object call() throws Exception {
    Thread.sleep(1000);
    return doSomeThing();
  }
}
