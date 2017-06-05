package exercise.java.datastructure.r_tree;

/**
 * Created by hanbing on 2017/6/5.
 */
public class Rectangle implements Cloneable {
    private Point low;
    private Point high;

    public Rectangle(Point p1, Point p2) throws CloneNotSupportedException {
        if(p1 == null || p2 == null){
            throw new IllegalArgumentException("not null in Rectangle");
        }
        if(p1.getDimension() != p2.getDimension()){
            throw new IllegalArgumentException("dimension must be equal in Rectangle");
        }
        for(int i = 0; i < p1.getDimension(); i++){
            if(p1.getValByIndex(i) > p2.getValByIndex(i))
                throw new IllegalArgumentException("left first, right end");
        }
        low = (Point)p1.clone();
        high = (Point)p2.clone();
    }

    public Point getLowPoint(){
        return low;
    }
    public Point getHighPoint(){
        return high;
    }

    /*
    包含两个最小矩形的最小矩形
     */
    public Rectangle getUnionRectangle(Rectangle rectangle) throws CloneNotSupportedException {
        if(rectangle == null)
            throw new IllegalArgumentException("rectangle not null in Rectangle");
        if(rectangle.getDimension() != getDimension())
            throw new IllegalArgumentException("dimension not equal");
        float[] min = new float[getDimension()];
        float[] max = new float[getDimension()];

        for(int i = 0; i < getDimension(); i++){
            min[i] = Math.min(low.getValByIndex(i), rectangle.low.getValByIndex(i));
            max[i] = Math.max(high.getValByIndex(i), rectangle.high.getValByIndex(i));
        }
        return new Rectangle(new Point(min), new Point(max));
    }

    public Rectangle getUnionRectangle(Rectangle[] rectangles) throws CloneNotSupportedException {
        if(rectangles == null || rectangles.length == 0)
            throw new IllegalArgumentException("rectangle is null");
        Rectangle r0 = rectangles[0];
        for(int i = 1; i < rectangles.length; i++){
            r0 = r0.getUnionRectangle(rectangles[i]);
        }
        return r0;
    }


    public int getDimension(){
        return low.getDimension();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Point p1 = (Point)low.clone();
        Point p2 = (Point)high.clone();
        return new Rectangle(p1, p2);
    }

    public float getArea(){
        float area = 1;
        for(int i = 0; i < getDimension(); i++){
            area *= high.getValByIndex(i) - low.getValByIndex(i);
        }
        return area;
    }

    public boolean isIntersection(Rectangle rectangle){
        if(rectangle == null)
            throw new IllegalArgumentException("rectangle is null");
        if(rectangle.getDimension() != getDimension())
            throw new IllegalArgumentException("rectangle error");
        for(int i =0; i < getDimension(); i++){
            if(low.getValByIndex(i) > rectangle.high.getValByIndex(i) || high.getValByIndex(i) < rectangle.low.getValByIndex(i))
                return false;
        }
        return true;
    }

    public float getIntersectionArea(Rectangle rectangle){
        if(!isIntersection(rectangle))
            return 0;
        float ret = 1;
        for(int i = 0; i < getDimension(); i++){
            float l1 = low.getValByIndex(i);
            float l2 = rectangle.low.getValByIndex(i);
            float h1 = high.getValByIndex(i);
            float h2 = rectangle.high.getValByIndex(i);

            if(l1 < l2 && h1 < h2) {
                ret *= h1 - l2;
            } else if(l1 > l2 && h1 > h2){
                ret *= h2 - l1;
            }else if(l1 > l2 && h1 < h2){
                ret *= h1 - l1;
            }else if(l1 < l2 && h1 > h2){
                ret *= h2 - l2;
            }
        }
        return ret;
    }


}
