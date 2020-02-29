package com.juc.schedule;

/**
 * @Author: LZJ
 * @Date: 2020/2/24 21:17
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {
        ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
        scheduleService.startJob(2);
    }

}
