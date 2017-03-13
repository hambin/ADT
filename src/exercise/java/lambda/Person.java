package exercise.java.lambda;

/**
 * Created by hanbing on 2017/3/9.
 */
public class Person {
    String id;
    String sex;
    String salar;

    public Person(){}
    public  Person(String id, String sex, String salar){
        this.id = id;
        this.salar = salar;
        this.sex = sex;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSalar() {
        return salar;
    }

    public void setSalar(String salar) {
        this.salar = salar;
    }
}
