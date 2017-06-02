package exercise.java.datastructure.r_tree;

import java.util.Arrays;

/**
 * Created by hanbing on 2017/6/2.
 */
public class Point implements Cloneable {
    private float[] data;

    public Point(float[] data) {
        if (data == null) {
            throw new IllegalArgumentException("not null!");
        }
        if (data.length < 2) {
            throw new IllegalArgumentException("dimension is to small");
        }
        this.data = new float[data.length];
        System.arraycopy(data, 0, this.data, 0, data.length);
    }

    public Point(int[] data) {
        if (data == null) {
            throw new IllegalArgumentException("not null");
        }
        if (data.length < 2) {
            throw new IllegalArgumentException("dimension is to small");
        }
        this.data = new float[data.length];
        System.arraycopy(data, 0, this.data, 0, data.length);
//        for(int i = 0; i < data.length; i++){
//            this.data[i] = data[i];
//        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        float[] clone = new float[data.length];
        System.arraycopy(data, 0, clone, 0, data.length);
        return new Point(clone);
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("(");
        for (int i = 0; i < data.length - 1; i++) {
            stringBuffer.append(data[i]).append(",");
        }
        stringBuffer.append(data[data.length - 1]).append(")");
        return stringBuffer.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Point){
            Point point = (Point)obj;
            if(data.length != point.getDimension())
                throw new IllegalArgumentException("dimension error");
            for(int i = 0; i < point.getDimension(); i++){
                if(data[i] != point.getValByIndex(i))
                    return false;
            }
        }else{
            return false;
        }
        return true;
    }

    public int getDimension() {
        return data.length;
    }

    public float getValByIndex(int index) {
        return data[index];
    }

}
