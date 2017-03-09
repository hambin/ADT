package com.util.array;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by hanbing on 2017/2/27.
 */
public class FindMaxSubstring {
    public static void main(String[] args) {
        int a = 100;
        int[] arr = generateRandomArray(1000000);
        //int[] arr = {-12, -100, 92, 1, 49, 60, -44, -14, -59, 20};
        System.out.println(Arrays.toString(arr));
        long startTime = new Date().getTime();
        findMaxSubstring(arr);
        System.out.println(new Date().getTime() - startTime + "ms");
    }

    static int[] generateRandomArray(int size) {
        int[] arr = new int[size];

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = 100 - random.nextInt(220);
        }
        return arr;
    }

    static void findMaxSubstring(int[] arr) {

        int max = 0;
        int sum = 0;
        int sum1 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max > arr.length - i)
                break;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum > 0)
                    if (max < j - i + 1)
                        max = j - i + 1;
            }
            sum = 0;
        }
        System.out.println(max);
        //全量相加为正，直接输出
        if (arr.length == max) {
            System.out.println(Arrays.toString(arr));
        } else {
            printArray(arr, max);
        }

    }

    static int sperate(int[] arr, int l, int r) {
        if (l > r) return 0;
        if (l == r) return 1;

        int m = (l + r) / 2;
        int lmax = 0, lsum = 0;
        for (int i = m; i >= 0; i--) {
            lsum += arr[i];
            if (lsum > 0) {
                if (lmax < m - i + 1) {
                    lmax = m - i + 1;
                }
            }
        }
        int rmax = 0, rsum = 0;
        for (int j = m + 1; j < arr.length; j++) {
            rsum += arr[j];
            if (rsum > 0) {
                if (rmax < j - m) {
                    rmax = j - m;
                }
            }
        }
        return maxReturn(lmax + rmax, sperate(arr, l, m), sperate(arr, m + 1, r));
    }

    static int maxReturn(int all, int l, int r) {
        if (all >= l) {
            if (all >= r)
                return all;
            else
                return r;
        } else {
            if (l >= r)
                return l;
            else
                return r;
        }
    }

    static void printArray(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    static void printArray(int[] arr, int max) {
        int sum1 = 0;
        for (int i = 0; i <= arr.length - max; i++) {
            int j = i;
            for (; j < i + max; j++) {
                sum1 += arr[j];
            }
            if (sum1 > 0) {
                printArray(arr, i, j);
            }
            sum1 = 0;
        }
    }
}
