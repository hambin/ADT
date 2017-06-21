package exercise.java.java_new_feature.stream;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;

/**
 * Created by hanbing on 2017/6/21.
 */
public class Demo {

    public static void main(String[] args){
        //素数分区
        Scanner scanner = new Scanner(System.in);

        Map<Boolean, List<Integer>> map = IntStream.rangeClosed(2, scanner.nextInt())
                .boxed()
                .collect(Collectors.partitioningBy(
                        candidate -> isPrime(candidate)));
        System.out.println(map.toString());
    }

    public static boolean isPrime(int candidate){
        return range(2, candidate).noneMatch(i -> candidate % i == 0);
    }
}
