package machine_learning.k_medoids;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by hanbing on 2017/6/7.
 */
public class ClusterAnalysis {
    private Cluster[] clusters;
    private int miter;
    private ArrayList<DataPoint> dataPoints = new ArrayList<>();
    private int dimNum;

    public static void main(String[] args) throws IOException {
        ArrayList<DataPoint> test = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D://LB.txt"));
        String line;
        int no = 1;
        while ((line = bufferedReader.readLine()) != null) {
            String[] splits = line.split(" ");
            float x1 = Float.parseFloat(splits[1]);
            float y1 = Float.parseFloat(splits[2]);
            float x2 = Float.parseFloat(splits[3]);
            float y2 = Float.parseFloat(splits[4]);
            double[] left = {x1, y1};
            double[] right = {x2, y2};
            double[] medoid = {(x2 + x1) / 2, (y2 + y1) / 2};
            test.add(new DataPoint(medoid, "" + no++, left, right));
        }
//        double[] a = {2, 3};
//        double[] b = {2, 4};
//        double[] c = {1, 4};
//        double[] d = {1, 3};
//        double[] e = {2, 2};
//        double[] f = {3, 2};
//
//        double[] g = {8, 7};
//        double[] h = {8, 6};
//        double[] i = {7, 7};
//        double[] j = {7, 6};
//        double[] k = {8, 5};
//
//        double[] l = {100, 200};//孤立点
////
//        double[] m = {8, 20};
//        double[] n = {8, 19};
//        double[] o = {7, 18};
//        double[] p = {7, 17};
//        double[] q = {7, 20};
//
//        test.add(new DataPoint(a, "a"));
//        test.add(new DataPoint(b, "b"));
//        test.add(new DataPoint(c, "c"));
//        test.add(new DataPoint(d, "d"));
//        test.add(new DataPoint(e, "e"));
//        test.add(new DataPoint(f, "f"));
//        test.add(new DataPoint(g, "g"));
//        test.add(new DataPoint(h, "h"));
//        test.add(new DataPoint(i, "i"));
//        test.add(new DataPoint(j, "j"));
//        test.add(new DataPoint(k, "k"));
//        test.add(new DataPoint(l, "l"));
//        test.add(new DataPoint(m, "m"));
//        test.add(new DataPoint(n, "n"));
//        test.add(new DataPoint(o, "o"));
//        test.add(new DataPoint(p, "p"));
//        test.add(new DataPoint(q, "q"));

        File writeName = new File("D://KMoutput.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
        writeName.createNewFile(); // 创建新文件
        BufferedWriter out = new BufferedWriter(new FileWriter(writeName));
        ClusterAnalysis ca = new ClusterAnalysis(4, 0, test, 2);
        double[][] medoids = {test.get(0).getData(), test.get(1).getData(), test.get(2).getData(), test.get(3).getData()};
        ca.startAnalysis(medoids);
        for (int index = 0; index < ca.clusters.length; index++) {
            for (int vi = 0; vi < ca.clusters[index].getDataPoints().size(); vi++) {
                //System.out.println(Arrays.toString(ca.clusters[index].getMedoid().getData()) + "   cluster : " + index);
                out.write(Arrays.toString(ca.clusters[index].getDataPoint(vi).getLeft()) +"---"+ Arrays.toString(ca.clusters[index].getDataPoint(vi).getRight()) + "   cluster : " + index + "\n\r");
            }
        }
        out.flush();
        out.close();
    }

    public ClusterAnalysis(int k, int iter, ArrayList<DataPoint> dataPoints, int dimNum) {
        clusters = new Cluster[k];
        for (int i = 0; i < k; i++) {
            clusters[i] = new Cluster("cluster" + i);
        }
        this.miter = iter;
        this.dataPoints = dataPoints;
        this.dimNum = dimNum;
    }

    public int getIterations() {
        return miter;
    }

    public ArrayList<DataPoint>[] getClusterOutput() {
        ArrayList<DataPoint>[] v = new ArrayList[clusters.length];

        for (int i = 0; i < clusters.length; i++) {
            v[i] = clusters[i].getDataPoints();
        }
        return v;
    }

    public void startAnalysis(double[][] medoids) {
        int it = 0;
        setInitialMedoids(medoids);
        double[][] newMedoids = medoids;
        double[][] oldMedoids = new double[medoids.length][dimNum];

        while (!isEqual(oldMedoids, newMedoids)) {
            System.out.println("iteration : " + ++it);
            for (int m = 0; m < clusters.length; m++) {
                clusters[m].getDataPoints().clear();
            }
            for (int i = 0; i < dataPoints.size(); i++) {
                int clusterIndex = 0;
                double minDis = Double.MAX_VALUE;

                for (int j = 0; j < clusters.length; j++) {
                    double eucDis = dataPoints.get(i).disToMedoid(clusters[j].getMedoid());
                    if (eucDis < minDis) {
                        minDis = eucDis;
                        clusterIndex = j;
                    }
                }

                clusters[clusterIndex].getDataPoints().add(dataPoints.get(i));
                dataPoints.get(i).setCluster(clusters[clusterIndex]);
            }

            for (int k = 0; k < clusters.length; k++) {
                clusters[k].getMedoid().calcMedoid();
            }
            for (int n = 0; n < medoids.length; n++) {
                for (int k = 0; k < medoids[n].length; k++) {
                    oldMedoids[n][k] = newMedoids[n][k];
                }
            }

            for (int n = 0; n < clusters.length; n++) {
                newMedoids[n] = clusters[n].getMedoid().getData();
            }
            this.miter++;
        }

    }

    private void setInitialMedoids(double[][] medoids) {
        for (int i = 0; i < clusters.length; i++) {
            Medoid medoid = new Medoid(medoids[i]);
            clusters[i].setMedoid(medoid);
            medoid.setCluster(clusters[i]);
        }
    }

    private boolean isEqual(double[][] oldMedoids, double[][] newMedoids) {
        for (int i = 0; i < oldMedoids.length; i++) {
            for (int j = 0; j < oldMedoids[i].length; j++) {
                if (oldMedoids[i][j] != newMedoids[i][j])
                    return false;
            }
        }
        return true;
    }
}
