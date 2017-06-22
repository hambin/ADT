package exercise.java.java_new_feature.stream.parallel_stream_performance;

import java.util.function.Function;

/**
 * Created by hanbing on 2017/6/22.
 */
public class Main {

    public static long measureSumPerf(Function<Long, Long> adder, long n){
        long fastest = Long.MAX_VALUE;

        for(int i = 0; i < 10; i++){
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result : " + sum);
            if(duration < fastest)
                fastest = duration;
        }
        return fastest;
    }


    public static void main(String[] args){
        System.out.println(measureSumPerf(ParallelStreams::sequentialSum, 10_000_000) + "mesc");
        System.out.println(measureSumPerf(ParallelStreams::iterativeSum, 10_000_000) + "mesc");
        System.out.println(measureSumPerf(ParallelStreams::parallelSum, 10_000_000) + "mesc");
        System.out.println(measureSumPerf(ParallelStreams::parallelSumWithoutBoxed, 10_000_000) + "mesc");
    }
}
