package Recursion;

/**
 * Created by hanbing on 2017/2/28.
 */
public class EatApple {
    /**
     * n - ( n/2 + 1)
     * 已知条件为第十天剩一个，那么函数变量为天数， f(10) = 1。
     * 1534
     */
    public static int recursion(int n) {
        if (10 == n) {
            return 1;
        } else {
            return 2 * (recursion(n + 1) + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(recursion(1));
    }
}
