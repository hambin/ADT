package exercise.java.comparator;

import java.util.*;

/**
 * Created by hanbing on 2017/3/30.
 */
public class UseOfComparable {

    public static void main(String[] args) {
        Person p1 = new Person("x", 20);
        Person p2 = new Person("a", 18);
        List<Person> persons = new ArrayList<>();
        persons.add(p1);
        persons.add(p2);


        List<Integer> sorted1 = Arrays.asList(9, 8, 7, 6, 5, 4, 43);

        Collections.sort(persons);
        Collections.sort(sorted1);


        persons.forEach(System.out::println);

        Collections.sort(persons, new DescComparator());
        persons.forEach(System.out::println);


        sorted1.forEach(System.out::println);


        Student s1 = new Student("s1", 6, 1);
        Student s2 = new Student("s2", 8, 2);
        List<Student> ss = new ArrayList<>();
        ss.add(s1);
        ss.add(s2);
        Collections.sort(ss);
        ss.forEach(System.out::println);

        SortedSet<String> stringSet = new TreeSet<>();
        stringSet.add("张");
        stringSet.add("哈哈");
        stringSet.add("李四");

        stringSet.forEach(System.out::println);

    }


    //外部比较器
    static class DescComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o2.age - o1.age;
        }
    }
}
