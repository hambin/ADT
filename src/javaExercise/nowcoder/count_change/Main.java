package javaExercise.nowcoder.count_change;

import java.util.Scanner;

/**
 * Created by hanbing on 2017/6/14.
 */
public class Main {
    static int count = 0;
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        while(n > 2000000000){
//            n = scanner.nextInt();
//        }
//
//        System.out.print(countOfChange(n));
        String str = "12345";
        String[] s = str.split("");
        for(int i = 0 ; i < s.length; i++){
            System.out.println(Integer.valueOf(s[i]) instanceof Integer);
        }
    }

    public static int countOfChange(int value) {

        String[] arr = String.valueOf(value).split("");
        if(arr.length > 1){
            int temp = 1;
            for(int i = 0; i < arr.length; i++){
                temp *= Integer.parseInt(arr[i]);
            }
            count++;
            countOfChange(temp);
        }
        return count;
    }
}



//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        int n = scanner.nextInt();
//        while(n > 2000000000){
//            n = scanner.nextInt();
//        }
//        //System.out.println(1);
//        ArrayList<Integer> list = new ArrayList<>();
//        String[] arr = String.valueOf(n).split("");
//        for(int i = 0; i < arr.length; i++){
//            list.add(Integer.valueOf(arr[i]));
//        }
//
//        int count = 0;
//
//        while (list.size() > 1) {
//
//            int tempInt = 1;
//            if (list.contains(0)) {
//                count++;
//                break;
//            }
//
//            for (int i = 0; i < list.size(); i++) {
//                tempInt *= list.get(i);
//            }
//            list.clear();
//            arr = String.valueOf(tempInt).split("");
//            for(int i = 0; i < arr.length; i++){
//                list.add(Integer.valueOf(arr[i]));
//            }
//            count++;
//        }
//
//        System.out.println(count);
//        //System.out.println(1);
//    }
//
//}
