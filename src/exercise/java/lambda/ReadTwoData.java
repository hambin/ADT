package exercise.java.lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by hanbing on 2017/3/6.
 */
public class ReadTwoData {
    public static void main(String[] args) throws IOException {
        Reader r = (buff)-> buff.readLine() + buff.readLine();
        String b = r.read(new BufferedReader(new FileReader("e:/a.txt")));
        String a = readFile(r);
        System.out.println(a + "   " + b);
    }

    public static String readFile(Reader r) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("e:/a.txt"))) {
            return r.read(bufferedReader);
        }
    }
}

@FunctionalInterface
interface Reader {
    String read(BufferedReader buff) throws IOException;
}
