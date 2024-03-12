import data.MyMaths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * KMeansClustering class containing main algorithm for unsupervised kMeansClustering execution.
 * The fitted model is used to make predictions about the current dataset.
 * */
public class KMeansClustering {

    /**
     * Number of classes to separate the data into.
     * */
    private int k;

    /**
     * Central point coordinates of multidimensional k numbered clusters on the graph.
     * Used to determine whether data point belongs to a class.
     * */
    private double[][] centroids;

    /**
     * Class constructor for initialization of k and centroids.
     *
     * @param k number of classes to separate data into
     * */
    public KMeansClustering(int k) {

        this.k = k;
        centroids = new double[k][];

    }

    /**
     * Returns the predicted classes for each of the data points passed.
     * Centroids are moved to central points of clusters of data.
     * Points closest to those centroids are then assigned that centroid's class.
     *
     * @param X             list containing dimensional parameters of the dataset's points
     * @param SEED          value used to randomize initial centroid coordinates
     * @param maxIterations max number of iterations to prevent model from trying to fit with no end
     * @return              predicted class of each data point
     * */
    public List<Integer> fit(List<double[]> X, long SEED,int maxIterations){

        List<Integer> y = new ArrayList<>();
        placeCentroids(X,SEED);

        for(int i=0;i<maxIterations;i++){

            y = new ArrayList<>();//EMPTY LABELS
            assignLabels(X,y);
            List<List<Integer>> clusterIndices = new ArrayList<>();

            for(int j=0;j<k;j++){

                List<Integer> trueWhere = new ArrayList<>();

                for(int z=0;z<y.size();z++){
                    if(y.get(z)==j)trueWhere.add(j);
                }

                clusterIndices.add(trueWhere);
            }

            //REPOSITION CENTROIDS
            double[][] clusterCentres = new double[centroids.length][];

            for(int j=0;j<clusterIndices.size();j++){

                if(clusterIndices.get(j).size()==0){

                    clusterCentres[j] = centroids[j];

                }else{

                    double[] newClusterpos = new double[centroids[0].length];
                    //GET COORDINATES OF CHOSEN CLUSTERS, TRANSPOSE, THEN FIND MEAN OF EACH DIMENSION
                    List<double[]> temp = new ArrayList<>();

                    for(int t=0;t<clusterIndices.get(j).size();t++){

                        temp.add(X.get(clusterIndices.get(j).get(t)));

                    }

                    temp = MyMaths.transpose(temp);

                    for(int z=0;z< newClusterpos.length;z++){

                        newClusterpos[z] = MyMaths.mean(temp.get(z));

                    }
                    //ASSIGN NEW CLUSTER POSITION
                    clusterCentres[j] = newClusterpos;

                }

            }

            double max = MyMaths.maxArrayValue(MyMaths.add(centroids,MyMaths.multiply(clusterCentres,-1)));

            if(max<0.0001){
                break;
            }else{
                centroids = clusterCentres;
            }

        }
        return y;
    }

    /**
     * Calculates the euclidean distance for each point in the dataset and all the centroid coordinates.
     * Assigns the closest centroid number as the current class prediction.
     *
     * @param X coordinates of each data point in the dataset passed
     * @param y contains labels of dataset's points
     * */
    private void assignLabels(List<double[]> X,List<Integer> y) {

        for(int i=0;i<X.size();i++){

            double[] distancesToCentroids = new double[centroids.length];

            for(int j=0;j<centroids.length;j++){
                distancesToCentroids[j] = MyMaths.euclideanDistance(centroids[j],X.get(i));
            }

            //GET CLUSTER NUMBER AND ADD TO LABELS
            y.add(MyMaths.indexOfSmallest(distancesToCentroids));

        }

    }

    /**
     * Places centroids at randomized coordinate on graph.
     * Coordinates are sensibly chosen between the ranges of each dimension's MIN-MAX value
     *
     * @param X    coordinates of each data point in the dataset passed
     * @param SEED value used to randomize initial centroid coordinates
     * */
    private void placeCentroids(List<double[]> X,long SEED){

        double[][] dataPointsT = MyMaths.transpose(X.toArray(new double[X.size()][X.get(0).length]));
        Random r = new Random(SEED);

        for(int i =0;i<k;i++) {

            double[] centroidCoords = new double[X.get(0).length];

            for (int j = 0; j < dataPointsT.length; j++) {

                //FIND RANGE ACROSS EACH DIMENSION STORED AND PLACE CENTROID SOMEWHERE IN-BETWEEN
                double min = Arrays.stream(dataPointsT[j]).min().getAsDouble();
                double max = Arrays.stream(dataPointsT[j]).max().getAsDouble();

                //TO PREVENT BOTTOMING OUT
                if(min==0)min=1e-7;
                if(max==0)max=2e-7;

                centroidCoords[j] = r.nextDouble(min, max);

            }

            centroids[i] = centroidCoords;

        }

    }

}
