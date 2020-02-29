package com.juc.schedule;

import sun.rmi.server.InactiveGroupException;

/**
 * @Author: LZJ
 * @Date: 2020/2/24 21:06
 * @Version 1.0
 */
public interface ScheduleService {

    public void startJob(int seconds);

    public void shutDown();

}
