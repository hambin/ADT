package Binary;

/**
 * Created by hanbing on 2017/3/1.
 */
public class BinaryAlgorithm {
    public static void leftShift() {
        int i = -56, j = -100, min = 1, max = Integer.MAX_VALUE + 2;
        //System.out.println(Integer.toBinaryString(i));
        //向左移31位
        //System.out.println(Integer.toBinaryString((i >> -1)));
        //System.out.println(Integer.valueOf(Integer.toBinaryString((i << -1)), 2).toString());
        System.out.println(Integer.toBinaryString(max));
        //System.out.println(Integer.toBinaryString(i << 2));
//bitmap
        int a = 10, b = 20;
        a ^= b;
        b ^= a;
        a ^= b;

        System.out.println("a : " + a + ", b : " + b);
    }

    public static void main(String[] args) {
        leftShift();
    }
}
