package machine_learning.k_medoids;

import java.util.ArrayList;

/**
 * Created by hanbing on 2017/6/7.
 */
public class ClusterAnalysis {
    private Cluster[] clusters;
    private int miter;
    private ArrayList<DataPoint> dataPoints = new ArrayList<>();
    private int dimNum;

    public ClusterAnalysis(int k, int iter, ArrayList<DataPoint> dataPoints, int dimNum){
        clusters = new Cluster[k];
        for(int i = 0; i < k; i++){
            clusters[i] = new Cluster("cluster" + i);
        }
        this.miter = iter;
        this.dataPoints = dataPoints;
        this.dimNum = dimNum;
    }
    public int getIterations(){
        return miter;
    }

    public ArrayList<DataPoint>[] getClusterOutput(){
        ArrayList<DataPoint>[] v = new ArrayList[clusters.length];

        for(int i = 0; i < clusters.length; i++){
            v[i] = clusters[i].getDataPoints();
        }
        return v;
    }

    public void startAnalysis(double[][] medoids){
        setInitialMedoids(medoids);
        double[][] newMedoids = medoids;
        double[][] oldMedoids = new double[medoids.length][dimNum];

        while(!isEqual(oldMedoids, newMedoids)){
            for(int m = 0; m < clusters.length; m++){
                clusters[m].getDataPoints().clear();
            }
            for(int i = 0; i < dataPoints.size(); i++){
                int clusterIndex = 0;
                double minDis = Double.MAX_VALUE;

                for(int j = 0; j < clusters.length; j++){
                    double eucDis = dataPoints.get(i).disToMedoid(clusters[j].getMedoid());
                    if(eucDis < minDis){
                        minDis = eucDis;
                        clusterIndex = j;
                    }
                }

                clusters[clusterIndex].getDataPoints().add(dataPoints.get(i));
            }

            for(int k = 0; k < clusters.length; k++){
                clusters[k].getMedoid().calcMedoid();
            }
            for(int n = 0; n < medoids.length; n++){
                for(int k = 0; k < medoids[n].length; k++){
                    oldMedoids[n][k] = newMedoids[n][k];
                }
            }

            for(int n = 0; n < clusters.length; n++){
                newMedoids[n] = clusters[n].getMedoid().getData();
            }
            this.miter++;
        }

    }

    private void setInitialMedoids(double[][] medoids){
        for(int i = 0; i < clusters.length; i++){
            Medoid medoid = new Medoid(medoids[i]);
            clusters[i].setMedoid(medoid);
        }
    }

    private boolean isEqual(double[][] oldMedoids, double[][] newMedoids){
        for(int i = 0; i < oldMedoids.length; i++){
            for(int j = 0; j < oldMedoids[i].length; j++){
                if(oldMedoids[i][j] != newMedoids[i][j])
                    return false;
            }
        }
        return true;
    }
}
