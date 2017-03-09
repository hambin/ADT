package exercise.java.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Created by hanbing on 2017/3/7.
 */
public class CompareMethod {

    public static List<Weight> map(List<Integer> list, Function<Integer, Weight> f){
        List<Weight> result = new ArrayList<Weight>();
        for(Integer e : list){
            result.add(f.apply(e));
        }
        return result;
    }

    public static void main(String[] args){
        List<Integer> weight = Arrays.asList(1,20,3,4,5);
        List<Weight> inventory = new ArrayList<>();
        inventory = map(weight, Weight::new);
        inventory.sort(Comparator.comparing(Weight::getWeight));
        System.out.println(inventory);
    }

}

class Weight{
    int weight;

    public Weight(int weight){
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return weight+"";
    }
}

