package exercise.java.comparator;

/**
 * Created by hanbing on 2017/3/31.
 */
public class Student extends Person{

    int no;

    public Student(String name, int age, int no){
        this.name = name;
        this.age= age;
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
//
//    @Override
//    public String toString() {
//        return name + " : "  + age + " : " + no;
//    }
}
