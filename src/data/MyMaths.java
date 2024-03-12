package data;

import java.util.*;
import java.util.function.Predicate;

/**
 * Personal maths library created to simplify future java projects.
 * Mostly uncommented.
 * IGNORE!
 * */
public class MyMaths {
    public static int indexOfSmallest(double[] x){
        int index = 0;
        double temp = Double.MAX_VALUE;
        for(int i=0;i<x.length;i++){
            if(x[i]<temp){
                temp=x[i];
                index=i;
            }
        }
        return index;
    }
    public static int indexOfLargest(double[] x){
        int index = 0;
        double temp = Double.MIN_VALUE;
        for(int i=0;i<x.length;i++){
            if(x[i]>temp){
                temp=x[i];
                index=i;
            }
        }
        return index;
    }
    public static double euclideanDistance(double[] x1,double[] x2){
        double sum=0;
        for(int i=0;i<x1.length;i++){
            sum+=Math.pow(x1[i]-x2[i],2);
        }
        return Math.sqrt(sum);
    }
    //GENERIC COUNTER BUILDER
    public static <T> Map<T,Integer> caseCounterBuilder(T[] cases){
        Map<T,Integer> counter = new HashMap<>();
        for(T o:cases){
            counter.put(o,0);
        }
        return counter;
    }
    /**________________________________________________________________________________
     *
     * MATRIX HELPERS BELOW
     *
     */

    public static double[][] add(double[][] a, double[][] b){

        double[][] out = new double[a.length][a[0].length];

        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                out[i][j] = a[i][j] + b[i][j];
            }
        }

        return out;
    }
    public static double[][] add(double[][] a, double b){

        double[][] out = new double[a.length][a[0].length];

        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                out[i][j] = a[i][j] + b;
            }
        }

        return out;
    }
    public static double[] add(double[] a, double[] b){

        double[] out = new double[a.length];

        for(int i=0;i<a.length;i++){
            out[i] = a[i] + b[i];
        }

        return out;
    }
    public static double[] add(double[] a, double b){

        double[] out = new double[a.length];

        for(int i=0;i<a.length;i++){
            out[i] = a[i] + b;
        }

        return out;
    }
    public static double[][] subtract(double[][] a,double[][] b){
        return add(a,multiply(b,-1));
    }
    public static double[] subtract(double[] a,double[] b){
        return add(a,multiply(b,-1));
    }
    public static double[][] multiply(double[][] a, double scalar){

        double[][] out = new double[a.length][a[0].length];

        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length;j++){
                out[i][j] = a[i][j]*scalar;
            }
        }

        return out;
    }
    public static double[] multiply(double[] a, double scalar){

        double[] out = new double[a.length];

        for(int i=0;i<a.length;i++){
            out[i] = a[i]*scalar;
        }

        return out;
    }
    public static double maxArrayValue(double[][] arr){
        double max = Double.MIN_VALUE;
        for (double[] doubles : arr) {
            for (double d : doubles) {
                max = max > d ? max : d;
            }
        }
        return max;
    }

    public static double[][] dotProduct(double[][] inputs, double[][] values){
        double[][] returnArr = new double[inputs.length][values[0].length];
        for(int i=0;i<returnArr.length;i++){
            for(int j=0;j<returnArr[0].length;j++){
                double val=0;
                for(int k=0;k<inputs[0].length;k++){
                    val+=inputs[i][k]*values[k][j];
                }
                returnArr[i][j]=val;
            }
        }
        return returnArr;
    }
    public static double[] dotProduct(double[][] inputs, double[] values){
        double[] dotProduct=new double[inputs.length];
        for(int i=0;i<inputs.length;i++){
            double sumProd=0;
            for(int j=0;j<inputs[0].length;j++){
                sumProd+=inputs[i][j]*values[j];
            }
            dotProduct[i]=sumProd;
        }
        return dotProduct;
    }
    public static double dotProduct(double[] inputs, double[] values){
        double dotProduct=0;
        for(int i=0;i<inputs.length;i++){
            dotProduct+=inputs[i]*values[i];
        }
        return dotProduct;
    }
    public static double[] mean(double[][] arr){
        double[] means = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            means[i] = mean(arr[i]);
        }
        return means;
    }
    public static double mean(double[] arr){
        double sum=0;
        for(double d:arr){
            sum+=d;
        }
        return sum/arr.length;
    }
    public static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }

    public static double[][] transpose(double[][] array){
        double[][] returnArr = new double[array[0].length][array.length];
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[0].length;j++){
                returnArr[j][i] = array[i][j];
            }
        }
        return returnArr;
    }
    public static List<double[]> transpose(List<double[]> list){
        List<double[]> returnList = new ArrayList<>();
        for(int i=0;i<list.get(0).length;i++){
            returnList.add(new double[list.size()]);
        }
        for(int i=0;i<list.size();i++){
            for(int j=0;j<list.get(0).length;j++){
                returnList.get(j)[i] = list.get(i)[j];
            }
        }
        return returnList;
    }
    public static List<Double> uniqueValues(double[][] arr){
        Set<Double> set = new HashSet<>();
        for(double[] doubleArr : arr) {
            for(double d:doubleArr){
                set.add(d);
            }
        }
        return new ArrayList<>(set);
    }
    public static List<Double> uniqueValues(double[] arr){
        Set<Double> set = new HashSet<>();
        for(double d:arr){
            set.add(d);
        }
        return new ArrayList<>(set);
    }
    public static double median(double[][] arr){
        double[] returnArr = flatten(arr);
        return median(returnArr);
    }
    public static double median(double[] arr){
        int len = arr.length;
        Arrays.sort(arr);
        if(len%2==0){
            return (arr[(len/2)-1]+arr[len/2])/2;
        }
        return arr[len/2];
    }
    public static double[] flatten(double[][] arr){
        double[] returnArr = new double[arr.length*arr[0].length];
        int k=0;
        for(double[] i:arr){
            for(double j:i){
                returnArr[k++] = j;
            }
        }
        return returnArr;
    }
    public static double[][] sign(double[][] arr){
        double[][] signArr = new double[arr.length][arr[0].length];
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                signArr[i][j] = signArr[i][j]>0?1:signArr[i][j]<0?-1:0;
            }
        }
        return signArr;
    }
    public static double[] variance(double[][] arr){
        double[] variance= new double[arr.length];
        for(int i=0;i<arr.length;i++){
            variance[i] = variance(arr[i]);
        }
        return variance;
    }
    public static double variance(double[] arr){
        double variance=0;
        double mean = mean(arr);
        for(double d:arr){
            variance+= Math.pow(d-mean,2);
        }
        return variance;
    }
    public static double standardDeviation(double[] arr){
        return Math.sqrt(variance(arr));
    }
    public static double[] sign(double[] arr){
        double[] signArr = new double[arr.length];
        for(int i=0;i<arr.length;i++){
            signArr[i] = arr[i]>0?1:arr[i]<0?-1:0;
        }
        return signArr;
    }
    public static double[][] absolute(double[][] arr){
        double[][] returnArr = new double[arr.length][arr[0].length];
        for(int i=0;i<arr.length;i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if(arr[i][j]>=0){
                    returnArr[i][j] = arr[i][j];
                }else{
                    returnArr[i][j] = arr[i][j]*-1;
                }
            }
        }
        return returnArr;
    }
    public static double[] absolute(double[] arr){
        double[] returnArr = new double[arr.length];
        for(int i=0;i<arr.length;i++) {
            if(arr[i]>=0){
                returnArr[i] = arr[i];
            }else{
                returnArr[i] = arr[i]*-1;
            }
        }
        return returnArr;
    }
    public static double[][] where(double[][] arr, Predicate<Double> predicate, double yes, double no){
        double[][] returnArr = new double[arr.length][arr[0].length];
        for(int i=0;i<arr.length;i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if(predicate.test(arr[i][j])){
                    returnArr[i][j] = yes;
                }else{
                    returnArr[i][j] = no;
                }
            }
        }
        return returnArr;
    }
    public static double[] where(double[] arr, Predicate<Double> predicate, double yes, double no){
        double[] returnArr = new double[arr.length];
        for(int i=0;i<arr.length;i++) {
            if(predicate.test(arr[i])){
                returnArr[i] = yes;
            }else{
                returnArr[i] = no;
            }
        }
        return returnArr;
    }
    public static int[] where(int[] arr, Predicate<Integer> predicate, int yes, int no){
        int[] returnArr = new int[arr.length];
        for(int i=0;i<arr.length;i++) {
            if(predicate.test(arr[i])){
                returnArr[i] = yes;
            }else{
                returnArr[i] = no;
            }
        }
        return returnArr;
    }
    /**_______________________________________________________
     *
     * SETS HELPER
     *
     * */
    public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
        Set<Set<T>> sets = new HashSet<Set<T>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<T>());
            return sets;
        }
        List<T> list = new ArrayList<T>(originalSet);
        T head = list.get(0);
        Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
        for (Set<T> set : powerSet(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }
}
