package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Data reader class.
 * IGNORE!
 * */
public class DataReader {
    public List<double[]> readDataWholesale(String path){
        List<double[]> data = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(path))) {
            //SKIP FIRST LINE WITH DATA LABELS
            String line= bf.readLine();
            while((line=bf.readLine())!=null){
                String[] lineItem = line.split(",");
                double[] lineData = new double[lineItem.length];
                for(int i=0;i<lineItem.length;i++){
                    lineData[i] = Double.parseDouble(lineItem[i]);
                }
                data.add(lineData);
            }
        }catch (Exception e){

        }
        return data;
    }

    public List<Image> readDataImages(String path){
        List<Image> images = new ArrayList<>();
        try(BufferedReader dataReader = new BufferedReader(new FileReader(path))){
            String line;
            while((line=dataReader.readLine()) != null){
                String[] lineItems = line.split(",");
                double[] data = new double[lineItems.length-1];
                int label = Integer.parseInt(lineItems[0]);
                for(int row=1;row<lineItems.length;row++){
                    data[row-1]=Integer.parseInt(lineItems[row]);
                }
                images.add(new Image(data,label));
            }
        }catch(Exception e){

        }
        return images;
    }
}
