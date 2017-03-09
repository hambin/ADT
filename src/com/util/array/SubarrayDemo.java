package com.util.array;

import java.util.*;

/**
 * Created by wangshuai on 2017/2/27.
 */
public class SubarrayDemo {
    private static Integer[] array =// {1, 9, 3, 8, 11, 4, 5, 6, 4, 19, 7, 1, 7 };
            //   {-71, -39, -62, -23, -19, -50, 14, 71, -34, 35, -10, -117, 0, 3, -14, 57, 77, -117, 29, -104, 40, -1, -45, -99, 98, 84, 62, 19, -114, -116, -16, 87, 10, 11, 21, -5, -88, -22, 92, 64, 20, -5, 54, 71, -26, -14, -22, -53, -113, 32, -42, -37, -109, 36, -40, -95, -28, 41, -90, -27, -57, -58, -21, 19, 99, -80, -62, 10, 70, -65, -61, -45, -62, -23, 66, -9, -86, -3, 21, 83, -66, -21, -118, -103, 40, -100, -28, 19, 32, -101, 61, 34, 94, -71, 34, -42, -116, 47, -40, -70};
          // {-81, 99, 14, 56, -95, 60, -14, 1, 83, 71, -66, -61, 10, 0, -63, -26, -64, -101, 41, -114, 25, -6, -60, 62, -112, -91, -18, -47, 0, 6, 38, -50, 5, -94, -15, -67, -32, 39, 36, -85, 57, 97, -70, 80, -74, -99, 20, -18, 75, 27, 31, 16, -85, -22, 29, 24, -100, -51, 10, 63, -79, 15, 37, -3, -44, -50, -119, -81, -47, -21, 76, -93, -105, -60, -96, -89, -33, 25, -101, -1, -63, -78, -101, 38, -2, -95, -17, -75, -86, -110, -85, -83, 1, -118, 27, 59, -21, 84, -23, 12};

            {-9, -113, 13, 54, -84, -105, 68, 79, 14, -88, -69, 82, -74, -23, 26, -89, 80, -68, -43, 94, -51, -101, 12, 38, -67, 37, -3, 30, 97, 70, -20, -108, -40, 65, -27, 19, -119, 12, -49, -99, 79, -97, -73, 12, 92, -42, 34, 75, 0, -56, -99, -118, -84, 47, 14, -93, -56, -100, -34, 38, -14, 65, 29, -97, 8, 13, -32, -64, -96, -39, 71, 8, -47, 91, 93, 56, -11, -54, 66, -5, -110, -9, -18, -96, 80, -10, -73, 82, 11, -65, 16, 3, -44, 94, 63, 39, 33, -6, 74, -14};
           // {95, -17, 88, -45, -102, -9, -117, -62, 96, 48, -20, -12, 99, 95, 47, -13, -40, 19, 0, 22, 40, 77, -75, -13, -108, -105, 53, -111, 12, -118, 3, -81, -111, 42, 97, -6, -1, -6, -41, -78, 35, 12, 82, 13, 4, 25, 76, -1, 60, 39, -81, 2, -23, -110, 13, -27, -72, 39, -39, -4, -54, 54, 87, -115, -85, -119, -72, -84, -30, 72, -90, 45, 39, -113, 45, -60, 26, 66, 9, -31, 95, -55, 34, -19, 97, 13, -93, -32, -6, 4, 38, -5, 83, -45, -21, 86, 82, 54, -94, -91};

    public static void main(String args[]) {

   //  Integer[] testArray = generateRandomArray(100000);
    Integer[] testArray = array;
        System.out.println("original" + Arrays.asList(testArray) + "-------------------");
        Long start = new Date().getTime();
        MaxSubSum(testArray, 0, testArray.length - 1);
        System.out.println("time:" + (new Date().getTime() - start) + "ms");
        Set set = new HashSet();
        set.addAll(result);
        result.clear();
        result.addAll(set);
        result.forEach(list -> {
            System.out.println(list);
            System.out.println("size:" + list.size());
            System.out.println("total:" + sumValue(resultList));
        });


    }

    static List<List<Integer>> result = new ArrayList<>();
    static List<Integer> resultList = new ArrayList<>();

    public static List<Integer> MaxSubSum(Integer[] arr, int left, int right) {

        int leftSum, rightSum;
        int i, center;
        //递归到最后
        if (left == right)
            return null;
        //求含中间边界的左右部分的大于0的下标
        center = (left + right) / 2;
        Integer leftIndex = center + 1;
        Integer rightIndex = center;
        leftSum = 0;
        for (i = center; i >= left; i--) {
            leftSum += arr[i];
            if (leftSum > 0) {
                leftIndex = i;
            }

        }
        rightSum = 0;
        for (i = center + 1; i <= right; i++) {
            rightSum += arr[i];
            if (rightSum > 0) {
                rightIndex = i;
            }

        }
        //截取目前最大长度子数组
        List<Integer> tempList = Arrays.asList(arr).subList(leftIndex, rightIndex + 1);
        Integer sum = sumValue(tempList);
        //left部分如果sum大于0则移动Index
        Integer changeIndex = leftIndex;
        for (i = leftIndex - 1; i >= 0; i--) {
            sum += arr[i];
            if (sum > 0) {
                changeIndex = i;
            }

        }
        leftIndex = changeIndex;
        //如果得到截取数组比原先的长则覆盖
        setMaxArr(Arrays.asList(arr).subList(leftIndex, rightIndex + 1));
        //right部分如果sum大于0则移动Index
        sum = sumValue(resultList);
        changeIndex = rightIndex;
        for (i = rightIndex + 1; i <= arr.length - 1; i++) {
            sum += arr[i];
            if (sum > 0) {
                changeIndex = i;
            }

        }
        rightIndex = changeIndex;
        //如果得到截取数组比原先的长则覆盖
        setMaxArr(Arrays.asList(arr).subList(leftIndex, rightIndex + 1));
        //递归求左右和中间部分最大值
        MaxSubSum(arr, left, center);
        MaxSubSum(arr, center + 1, right);
        return resultList;
    }


    public static int sumValue(List<Integer> maxList) {
        int r = 0;
        for (int i : maxList) {
            r += i;
        }
        return r;
    }


    public static void setMaxArr(List<Integer> tempList) {
        if (tempList.size() >= resultList.size() && sumValue(tempList) > 0) {

            if (tempList.size() == resultList.size()) {
                result.add(tempList);
            } else {
                resultList = tempList;
                result.clear();
                result.add(resultList);
            }

        }

    }

    static Integer[] generateRandomArray(int size) {
        Integer[] arr = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = 100 - random.nextInt(220);
        }
        return arr;
    }
}
