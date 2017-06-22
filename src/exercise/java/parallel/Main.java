package exercise.java.parallel;

import exercise.java.parallel.fork_join.ForkJoin;

import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by hanbing on 2017/6/22.
 */
public class Main {
    public static void main(String[] args){
        ForkJoin forkJoin = new ForkJoin();
        long[] numbers = LongStream.rangeClosed(1, 10_000_000).toArray();

        System.out.println(forkJoin.sumUp(numbers));

        final String SENTENCE = " Nel mezzo del cammin di nostra vita " +
                "mi ritrovai in una selva oscura" +
                " ché la dritta via era smarrita ";
        Stream<Character> characterStream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        characterStream.forEach(System.out::print);
    }
}
