package com.juc.recursiveaction;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

/**
 * @Author: LZJ
 * @Date: 2020/2/23 14:28
 * @Version 1.0
 */
public class MergeSortAction extends RecursiveAction {

    private static final Logger LOGGER = Logger.getLogger(MergeSortAction.class.getName());
    private final int threshold;
    private int[] arrayToSort;

    public MergeSortAction(int[] arrayToSort,int threshold) {
        this.threshold = threshold;
        this.arrayToSort = arrayToSort;
    }

    @Override
    protected void compute() {
        if (arrayToSort.length<=threshold){
            Arrays.sort(arrayToSort);
            return;
        }

        int midpoint = arrayToSort.length/2;
        int[] leftArray = Arrays.copyOfRange(arrayToSort,0,midpoint);
        int[] rightArray = Arrays.copyOfRange(arrayToSort,midpoint,arrayToSort.length);

        MergeSortAction left = new MergeSortAction(leftArray,threshold);
        MergeSortAction right = new MergeSortAction(rightArray,threshold);

        left.fork();
        right.fork();

        left.join();
        right.join();

        arrayToSort = MergeSortMain.merge(left.getSortedArray(), right.getSortedArray());
    }

    public int[] getSortedArray() {
        return arrayToSort;
    }
}
