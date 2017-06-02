package exercise.java.datastructure.morton_code;

/**
 * Created by hanbing on 2017/4/13.
 */
public class ForLoopMethod {

    public int getBitsPerValue(int dim){
        return Math.min(32, 64 / dim);
    }

    public long getMax(int dim){
        return (1L << getBitsPerValue(dim)) - 1;
    }

    public long interleave(long[] values){
        int dim = values.length;
        int bitsPerValue = 16;
        long x = 0;
        for(int i = 0; i < dim; i++){
            long k = values[i];
            if(k > getMax(dim)){
                throw new IllegalArgumentException(0 + "<" + k + "<" + getMax(dim));
            }
            for(int j = 0; j < bitsPerValue; j++){
                x |= (k & (1L << j)) << (i + (dim - 1) * j);
                //0000 0000 0000 0000 0000 0000 0000 1111
                //0000 0000 0000 0000 0000 0000 0000 0001  |                                                  ->0000 0000 0000 0000 0000 0000 0001 0001
                //0000 0000 0000 0000 0000 0000 0000 0010 -> 0000 0000 0000 0000 0000 0000 0001 0000
                //0000 0000 0000 0000 0000 0000 0000 0100 -> 0000 0000 0000 0000 0000 0001 0000 0000
            }
        }
        return x;
    }
}
