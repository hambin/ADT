package javaExercise.codeM.preTwo;

import java.util.*;

/**
 * Created by hanbing on 2017/6/13.
 *
 * 比赛有 n 个人参加（其中 n 为2的幂），每个参赛者根据资格赛和预赛、复赛的成绩，会有不同的积分。比赛采取锦标赛赛制，分轮次进行，设某一轮有 m 个人参加，那么参赛者会被分为 m/2 组，每组恰好 2 人，m/2 组的人分别厮杀。我们假定积分高的人肯定获胜，若积分一样，则随机产生获胜者。获胜者获得参加下一轮的资格，输的人被淘汰。重复这个过程，直至决出冠军。
 现在请问，参赛者小美最多可以活到第几轮（初始为第0轮）？
 *
 *思想：排序然后与中值比较
 */
public class Main {
    static int count = -1;
    public static void main(String[] args){

        Random random = new Random();
        int init = 2;
        List<Integer> arr = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while(n > Math.pow(2, 20) || n < 1 || ((n & n-1) != 0)){
            n = scanner.nextInt();
        }

        //arr = new int[n];
        for(int i = 0; i < n; i++){
            arr.add(random.nextInt());
        }
        int value = arr.get(0);
        Collections.sort(arr);
        int count = findMax(value, arr, 0, arr.size());
        System.out.println(arr.toString());
        System.out.println(count);
    }

    public static int findMax(int value, List arr, int start, int end){
        while(arr.contains(value)){
            int index = arr.lastIndexOf(value);
            if(index % 2 == 0){
                for(int i = 1; i < arr.size() - 1; i = i + 2)
                    arr.remove(arr.get(i));
                arr.remove(arr.get(0));
            }else{
                for(int i = 0; i < arr.size(); i = i+2)
                    arr.remove(arr.get(i));
            }
            if(!arr.contains(value))
                break;
            count++;
        }
        return count + 1;
    }

}
