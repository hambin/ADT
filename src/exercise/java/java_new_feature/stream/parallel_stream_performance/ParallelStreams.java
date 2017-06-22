package exercise.java.java_new_feature.stream.parallel_stream_performance;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by hanbing on 2017/6/22.
 */
public class ParallelStreams {
    //reduce给定初始值，返回long，否则返回Optional
    public static long sequentialSum(long n){
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    public static long iterativeSum(long n){
        long result = 0;
        for(long i = 0; i <= n; i++){
            result += i;
        }
        return  result;
    }

    public static long parallelSum(long n){
        return Stream.iterate(1L, i -> i+1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long parallelSumWithoutBoxed(long n){
        return LongStream.rangeClosed(1L, n)
                .parallel()
                .reduce(0L, Long::sum);
    }
}
