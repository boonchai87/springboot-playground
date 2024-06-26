package com.nengboonchai.demo.basic;

import java.util.Arrays;
import java.util.List;

public class Quiz {
    public static void main(String[] args) {
        // "static void main" must be defined in a public class.
        // input: [7,2,3,9,5,4]// 4 , 3 , 2 , 1 ,0
        // output: [5,9,3,2,7]// 0, 1, 2 , 3,  4,
        Integer[] numbers = new Integer[] { 7,2,3,9,5,4 };
        List<Integer> list = Arrays.asList(numbers);
        //System.out.println(list.size()/2);
        int max = (list.size()/2);
        int size = list.size();
        for(int i=0;i<max;i++){
            int targetIndex = i;
            int targetValue = list.get(targetIndex);
            int swapIndex = size-1-i;
            int swapValue = list.get(swapIndex);
            // System.out.println(swapIndex+","+targetIndex);
            // swap
            list.set(targetIndex,swapValue);
            list.set(swapIndex,targetValue);
        }
        System.out.println(list);
    }
}
