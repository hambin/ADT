package exercise.java.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hanbing on 2017/3/9.
 */
public class MapByStream {
    public  void a() {
        List<Person> inventory = Arrays.asList(new Person("1", "1", "2"), new Person("2", "2", "8"), new Person("2", "2", "8"));
        Map<String, Integer> map = b(inventory.stream(), i->i.getId());
        for(Map.Entry<String, Integer> m : map.entrySet()){
            System.out.println(m.getKey() + " " + m.getValue());
        }
        Map<String, List<Person>> map1 = inventory.stream().collect(Collectors.groupingBy(Person::getSex));
        Map<String, Integer> map2 = inventory.stream()
                .map(this::c).collect(Collectors.groupingBy(e->e.getId(), Collectors.summingInt(e->Integer.parseInt(e.getSalar()))));
        for(Map.Entry<String, List<Person>> m : map1.entrySet()){
            System.out.println(m.getKey() + " " + m.getValue());
        }
        for(Map.Entry<String, Integer> m : map2.entrySet()){
            System.out.println(m.getKey() + " " + m.getValue());
        }
//        inventory.forEach(e-> System.out.println(e.getSex()));
    }

    public  Person c(Person p){
        Person person = new Person();
        person.setId(p.getId());
        person.setSex(p.getSex());
        person.setSalar(p.getSalar());
        return person;
    }
    public  Map<String, Integer> b(Stream<Person> stream, Function<Person, String> function){
        return stream.collect(Collectors.groupingByConcurrent(function::apply, Collectors.summingInt(e->Integer.parseInt(e.getSalar()))));
    }
    public static void main(String[] args){
        MapByStream mbs = new MapByStream();
        mbs.a();
    }
}
