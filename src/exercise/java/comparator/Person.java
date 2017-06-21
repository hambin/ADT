package exercise.java.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Created by hanbing on 2017/3/30.
 */
public class Person implements Comparable<Person> {

    int age;
    String name;

    public Person() {
    }

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " : " + age;
    }


    public int compareTo(Person p) {
        return this.age - p.age;
    }

    public static void main(String[] args){
        List<Person> persons = new ArrayList<>();
        BiFunction<String, Integer, Person> p1 = Person::new;
        persons.add(p1.apply("a", 20));
        BiFunction<String, Integer, Person> p2 = Person::new;
        persons.add(p2.apply("b", 10));

        persons.sort(Comparator.comparing(Person::getAge));

        persons.forEach(System.out::println);
    }

}
