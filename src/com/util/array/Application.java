package com.util.array;

import java.lang.reflect.Array;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Created by hanbing on 2016/12/15.
 */
public class Application {
    public static void main(String[] args){
        int[] arr = new int[10000];

        for(int i = 0; i< 10000;i++){
            Random random = new Random();
            arr[i] = random.nextInt(100000);
        }
        SortUtil sort = new SortUtil();
        long startTime = new Date().getTime();
        sort.bubble(arr);
        System.out.println(new Date().getTime() - startTime + "ms");
        System.out.println(Arrays.toString(arr));


        int[] arr1 = new int[10000];
        arr1[0] = 100000;
        for(int i = 1; i< 10000;i++){
            Random random = new Random();
            arr1[i] = random.nextInt(100000);
        }
        long newStart = new Date().getTime();
        sort.newBubble(arr1);
        System.out.println(new Date().getTime() - newStart + "ms");
        System.out.println(Arrays.toString(arr1));



        int[] arr2 = new int[10000];
        arr2[0] = 100000;
        for(int i = 1; i< 10000;i++){
            Random random = new Random();
            arr2[i] = random.nextInt(100000);
        }

        long insertStart = new Date().getTime();
        sort.newBubble(arr2);
        System.out.println(new Date().getTime() - insertStart + "ms");
        System.out.println(Arrays.toString(arr2));


        int[] delRepeat = new int[]{1,1,1,1,2,2,3,6,7,5,4,4,4};
        ArrayUtil arrayUtil = new ArrayUtil();
        int size = arrayUtil.delRepeation(delRepeat);
        for(int i =0; i < size; i++){
            System.out.print(delRepeat[i] + " ");
        }
    }


}
