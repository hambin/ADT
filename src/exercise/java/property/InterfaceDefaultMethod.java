package exercise.java.property;

/**
 * Created by hanbing on 2017/3/3.
 */
public class InterfaceDefaultMethod implements defaultInterface{
    @Override
    public void method1() {

    }

    public static void main(String[] args){
        InterfaceDefaultMethod i = new InterfaceDefaultMethod();
        i.method2();
    }
}

interface defaultInterface{
    public void method1();
    default void method2(){
        System.out.println("have not to implement.");
    };
}
