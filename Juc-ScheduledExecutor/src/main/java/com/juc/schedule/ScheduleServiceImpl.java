package com.juc.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: LZJ
 * @Date: 2020/2/24 21:07
 * @Version 1.0
 */
public class ScheduleServiceImpl implements ScheduleService {

    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

    @Override
    public void startJob(int seconds) {
        executorService.scheduleAtFixedRate(new Monitor(),0, seconds,TimeUnit.SECONDS);
//        executorService.scheduleWithFixedDelay(new Monitor(),0, seconds,TimeUnit.SECONDS);
    }

    @Override
    public void shutDown() {
        executorService.shutdown();
    }

}
