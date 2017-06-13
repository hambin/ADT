package javaExercise.codeM.preOne;

import java.util.Scanner;

/**
 * Created by hanbing on 2017/6/13.
 * 就是在第二段音频中找到一个长度和第一段音频相等且是连续的子序列，使得它们的 difference 最小。两段等长音频的 difference 定义为：
 difference = SUM(a[i] - b[i])2 (1 ≤ i ≤ n),其中SUM()表示求和
 其中 n 表示序列长度，a[i], b[i]分别表示两段音频的音高。现在袋鼠先生想要知道，difference的最小值是多少？数据保证第一段音频的长度小于等于第二段音频的长度。
 */
public class Main {
    public static void main(String[] args){
        int [] a, b;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (n > 1000 || n < 1){
            n = scanner.nextInt();
        }
        a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        while (m > 1000 || m < n){
            m = scanner.nextInt();
        }
        b = new int[m];
        for(int j = 0; j < m; j++){
            b[j] = scanner.nextInt();
        }

        int diff = findMinDiff(a, b);
        System.out.println(diff);
    }

    public static int findMinDiff(int[] a, int[] b){
        int diff = Integer.MAX_VALUE;

        for(int i = 0; i <= b.length - a.length; i++){
            int sum = 0;
            int indexB = i;
            for(int j = 0; j < a.length; j++){
                sum += (int)Math.pow((b[indexB++] - a[j]), 2);
            }
            if(sum < diff)
                diff = sum;
        }

        return diff;
    }
}
