package com.util.array;

/**
 * Created by hanbing on 2016/12/8.
 */
public class ArrayUtil {


    public int delRepeation(int[] a){
        int NIL = Integer.MIN_VALUE;
        for(int i = 0; i < a.length-1; i++){
            if(a[i] == a[i+1]){
                a[i] = NIL;
            }
        }
        int size = 0;
        for(int i=0; i < a.length;i++){
            if(a[i]!= NIL){
                a[size] = a[i];
                size++;
            }
        }
        return size;
    }
}
