package machine_learning.k_medoids;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanbing on 2017/6/7.
 */
public class Cluster {
    private String clusterName;
    private double[] medoid;
    private ArrayList<DataPoint> dataPoints;

    public Cluster(String clusterName){
        this.clusterName = clusterName;
        this.medoid = null;
        dataPoints = new ArrayList<>();
    }
    public ArrayList getDataPoints(){
        return dataPoints;
    }
    public DataPoint getDataPoint(int index){
        return this.dataPoints.get(index);
    }
}
