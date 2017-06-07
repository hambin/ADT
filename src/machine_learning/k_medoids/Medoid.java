package machine_learning.k_medoids;

import java.util.ArrayList;

/**
 * Created by hanbing on 2017/6/7.
 */
public class Medoid {
    private Cluster cluster;
    private double[] data;
    private double etdDisSum;

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public double[] getData() {
        return data;
    }

    public double getEtdDisSum() {
        return etdDisSum;
    }

    public void setEtdDisSum(double etdDisSum) {
        this.etdDisSum = etdDisSum;
    }

    public Medoid(double[] data){
        this.data = data;
    }

    public void calcMedoid(){
        calcEtdDisSum();
        double minDis = this.etdDisSum;
        ArrayList<DataPoint> dataPoints = this.cluster.getDataPoints();
        for(int i = 0; i < dataPoints.size(); i++){
            double temp = dataPoints.get(i).calEuclideanDistanceSum();
            if(temp < minDis){
                data = dataPoints.get(i).getData();
                etdDisSum = temp;
            }
        }

    }

    public void calcEtdDisSum(){
        double sum = 0;
        double temp;
        ArrayList<DataPoint> dataPoints = this.cluster.getDataPoints();

        for(int i = 0; i < dataPoints.size(); i++){
            DataPoint dataPoint = dataPoints.get(i);
            for(int j = 0; j < data.length; j++){
                temp = Math.pow((data[j] - dataPoint.getData()[j]), 2);
                sum += temp;
            }
        }
        etdDisSum = Math.sqrt(sum);
    }

}
