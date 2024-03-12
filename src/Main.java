import data.DataReader;
import data.DataSeparater;
import data.Image;
import data.MyMaths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    /**
     * Static main function that initializes data container object and kMeansClustering object.
     * Prints out the accuracy of the model after prediction is made.
     * No filter was used for the data so accuracy is low.
     * */
    public static void main(String[] args) {
        /*int clusterCount = 10;

        List<Image> testData = new DataReader().readDataImages("data/mnist/mnist_test.csv");
        DataSeparater dataSeparater = new DataSeparater(testData);

        KMeansClustering kMeansClustering = new KMeansClustering(clusterCount);
        List<Integer> predictedLabels = kMeansClustering.fit(dataSeparater.getTestDataNoLabels(),4,200);

        double accuracy = accuracy(kMeansMatcher(dataSeparater.getTestLabels(),predictedLabels,clusterCount),predictedLabels);
        System.out.println("Accuracy: "+accuracy);
         */
    }

    /**
     * Returns the calculated accuracy of the model's predictions.
     * The predictedLabels field must first be matched to the most likely class as the model
     * is unsupervised and chosen classes do not necessarily map directly onto actual class representation.
     *
     * @param labels          an array of integers representing actual class of the passed data
     * @param predictedLabels a list of integers representing model's predictions of classes of passed data
     * @return                the accuracy of the model
     * */
    private static double accuracy(int[] labels,List<Integer> predictedLabels){

        double correct=0;

        for(int i=0;i<labels.length;i++){

            if(labels[i]==predictedLabels.get(i)){
                correct++;
            }

        }

        return correct/labels.length;
    }

    /**
     * Returns the matched classes based on predictions.
     * The predictedLabels field is matched to the most likely class from the actual class labels
     * and this is used to test accuracy.
     *
     * @param labels          an array of integers representing actual class of the passed data
     * @param predictedLabels a list of integers representing model's predictions of classes of passed data
     * @param clusterCount    number of clusters used in model
     * @return                matched classes
     * */
    private static int[] kMeansMatcher(List<Integer> labels,List<Integer> predictedLabels,int clusterCount){

        Map<Integer,double[]> map = new HashMap<>();

        for(int i=0;i<labels.size();i++){

            double[] newArr = map.getOrDefault(labels.get(i),new double[clusterCount]);
            newArr[predictedLabels.get(i)]+=1;
            map.put(labels.get(i),newArr);

        }

        int[] returnInt = new int[map.size()];

        for(int i=0;i<map.size();i++){
            returnInt[i] = MyMaths.indexOfLargest(map.get(i));
        }

        return returnInt;
    }
}