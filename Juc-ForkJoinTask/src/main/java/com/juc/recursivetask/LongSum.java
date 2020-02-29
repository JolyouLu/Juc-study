package com.juc.recursivetask;

import java.util.concurrent.RecursiveTask;

/**
 * @Author: LZJ
 * @Date: 2020/2/23 15:06
 * @Version 1.0
 */
public class LongSum extends RecursiveTask<Long> {

    static final int SEQUENTIAL_THRESHOLD = 1000;
    static final long NPS = (1000L * 1000 * 1000);
    static final boolean extraWork = true; // change to add more than just a sum

    int low;
    int high;
    int[] array;

    LongSum(int[] arr, int lo, int hi) {
        array = arr;
        low = lo;
        high = hi;
    }

    @Override
    protected Long compute() {
        if (high - low <= SEQUENTIAL_THRESHOLD){

            long sum = 0;
            for(int i = low; i < high; ++i){
                sum += array[i];
            }

            return sum;

        }else {
            int mid = low + (high - low) /2;
            LongSum left = new LongSum(array,low,mid);
            LongSum right = new LongSum(array,mid,high);
            left.fork();
            long rightAns = right.compute();
            long leftAns = left.join();
            return leftAns + rightAns;
        }
    }
}
