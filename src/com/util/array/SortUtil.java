package com.util.array;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by hanbing on 2016/12/15.
 */
public class SortUtil {

    public void bubble(int[] a) {
        int size = a.length;
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }

    }

    public void newBubble(int[] a) {
        int startIndex = 0;
        int endIndex = a.length - 1;
        int in;

        for (; startIndex < endIndex; startIndex++, endIndex--) {
            for(in = startIndex;in<endIndex;in++){
                if(a[in] >a[in+1]){
                    a[in] = a[in+1] +(a[in+1] = a[in]) *0;
                }
            }
            for(in= endIndex;in>startIndex;in--){
                if(a[in] <a[in-1]){
                    a[in] = a[in-1] + (a[in-1] = a[in])*0;
                }
            }
        }

//        while(startIndex < endIndex){
//            for(int i = startIndex+1; i <= endIndex;i++){
//                if(a[i] < a[startIndex]){
//                    int tmp = a[i];
//                    a[i] = a[startIndex];
//                    a[startIndex] = tmp;
//                }
//            }
//            startIndex++;
//
//            if(startIndex >= endIndex)
//                break;
//
//            for(int j = endIndex-1; j> startIndex; j--){
//                if(a[j] > a[endIndex]){
//                    int tmp = a[j];
//                    a[j] = a[endIndex];
//                    a[endIndex] = tmp;
//                }
//            }
//        }
    }

    //插入排序
    //逐个移动，为插入者腾出位置
    public void insertion(int[] a){
        for(int i = 1; i < a.length;i++){
            int j = i;
            int tmp = a[i];
            while(j >0 && a[j-1] > tmp){
                a[j] = a[j-1];
                j--;
            }
            a[j] = tmp;
        }
    }
}
