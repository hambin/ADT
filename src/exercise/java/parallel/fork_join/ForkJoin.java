package exercise.java.parallel.fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by hanbing on 2017/6/22.
 */
public class ForkJoin {

    private ForkJoinPool pool;

    public static class SumTask extends RecursiveTask<Long> {
        private int from;
        private int to;
        private long[] numbers;

        public SumTask(long[] numbers, int from, int to){
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        public SumTask(long[] numbers){
            this(numbers, 0, numbers.length);
        }

        @Override
        protected Long compute() {
            if(to - from < 6){
                long total = 0;
                for(int i = from; i < to; i++){
                    total += numbers[i];
                }
                return total;
            }else{
                int mid = (from + to) / 2;
                SumTask leftTask = new SumTask(numbers, from, mid);
                SumTask rightTask = new SumTask(numbers, mid, to);
                leftTask.fork();
                rightTask.fork();

                return leftTask.join() + rightTask.join();
            }
        }
    }

    public ForkJoin(){
        pool = new ForkJoinPool();
    }

    public long sumUp(long[] numbers){
        return pool.invoke(new SumTask(numbers, 0, numbers.length));
    }
}
