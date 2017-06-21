package exercise.java.java_new_feature.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hanbing on 2017/6/20.
 */
public class Main {

    public static void main(String[] args){
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );

        List<String> result = menu.stream().filter(Dish::isVegetarian).map(Dish::getName).collect(Collectors.toList());



        //distinct
        List<String> words = Arrays.asList("hello", "world");
        List<String[]> resultOfWords = words.stream().map(word -> word.split("")).distinct().collect(Collectors.toList());
        resultOfWords.stream().forEach(word -> System.out.println(Arrays.toString(word)));
        Stream<String> streamTemp = words.stream().map(word -> word.split("")).flatMap(Arrays::stream);

        List<Integer> nums1 = Arrays.asList(1,2,3,4,5);
        List<Integer> nums2 = Arrays.asList(9,8,7,6);

        List<int[]> pairs = nums1.stream().flatMap(i -> nums2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());
        //求乘积
        Optional<Integer> optional = nums1.stream().reduce((a, b) -> a * b);
        System.out.println(optional.orElse(0));
        //求最大值
        Optional<Integer> max = nums1.stream().reduce((a, b) -> a > b ? a : b);
        System.out.println(max.orElse(0));
        //求流中元素总数
        long count = menu.stream().count();
        System.out.println(count);
        Optional<Integer> optionalCount = menu.stream().map(d -> 1).reduce(Integer::sum);
        System.out.println(optionalCount.orElse(0));
    }
}
