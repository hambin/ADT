package exercise.java.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by hanbing on 2017/3/3.
 */
public class Filter {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("green", "1"));
        apples.add(new Apple("red", "2"));
        List<Apple> result = filter(apples, Filter::isGreenApple);
        System.out.println(result.size());
        //?这不能用" :: "？
        List<Apple> redApples = filter(apples, (Apple apple)->"red".equals(apple.getColor()));
        System.out.println(redApples.size());
        Apple s = create(()->new Apple("green", "19"));
        System.out.println(s.getWeight());

        int num = 110;
        Runnable r = ()-> System.out.println(num);

    }

    static Apple create(Supplier<Apple> apple){
        return apple.get();
    }

    static List<Apple> filter(List<Apple> apples, Predicate<Apple> p) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (p.test(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

    static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }
}

class Apple {
    String color;
    String weight;

    Apple(String color, String weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
