package exercise.java.comparator;

/**
 * Created by hanbing on 2017/3/30.
 */
public class Person implements Comparable<Person>{

        int age;
        String name;

        public Person( String name, int age) {
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
            return  -(p.age - this.age);
        }

}
