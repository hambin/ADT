package exercise.java.comparator;

import java.util.*;

/**
 * Created by hanbing on 2017/3/30.
 */
public class UseOfComparable{

    public static void main(String[] args){
        Person p1 = new Person("x", 20);
        Person p2 = new Person("a", 18);
        List<Person> persons = new ArrayList<>();
        Person[] ps = new Person[]{p1,p2};
        int[] sorted = new int[]{9,8,7,6,5,4,43};
        List<Integer> sorted1 = Arrays.asList(9,8,7,6,5,4,43);
        persons.add(p1);
        persons.add(p2);
        Collections.sort(persons);
        Collections.sort(sorted1);
        Arrays.sort(ps);
        Arrays.sort(sorted);

        persons.forEach(System.out::println);
        Collections.sort(persons, new DescComparator());
        persons.forEach(System.out::println);
        sorted1.forEach(System.out::println);
    }

    static class DescComparator implements Comparator<Person>{
        @Override
        public int compare(Person o1, Person o2) {
            return o2.age - o1.age;
        }
    }
}
