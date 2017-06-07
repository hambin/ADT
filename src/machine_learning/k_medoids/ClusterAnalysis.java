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
}
