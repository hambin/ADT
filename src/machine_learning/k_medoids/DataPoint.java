package machine_learning.k_medoids;

import java.util.ArrayList;

/**
 * Created by hanbing on 2017/6/7.
 */
public class DataPoint {

    private double[] data;
    private Cluster cluster;
    private String pointName;
    private double euDt;

    public double[] getData() {
        return data;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public double getEuDt() {
        return euDt;
    }

    public void setEuDt(double euDt) {
        this.euDt = euDt;
    }

    public DataPoint(double[] data, String pointName){
        this.data = data;
        this.pointName = pointName;
        this.cluster = null;
    }

    public double calEuclideanDistanceSum(){
        double sum = 0;
        double temp;
        Cluster cluster = this.getCluster();
        ArrayList<DataPoint> dataPoints = cluster.getDataPoints();

        for(int i = 0; i < dataPoints.size(); i++){
            DataPoint dataPoint = dataPoints.get(i);
            for(int j = 0; j < data.length; j++){
                temp = Math.pow((this.data[i] - dataPoint.data[i]), 2);
                sum += temp;
            }
        }

        return Math.sqrt(sum);
    }

    public double disToMedoid(Medoid medoid){
        double sum = 0;
        for(int i = 0; i < data.length; i++){
            double temp = Math.pow((data[i] - medoid.getData()[i]), 2);
            sum += temp;
        }
        return Math.sqrt(sum);
    }

    public Cluster getCluster(){
        return cluster;
    }
}
