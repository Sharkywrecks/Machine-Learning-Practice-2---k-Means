package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * Data separater class container
 * IGNORE!
 * */
public class DataSeparater {
    List<Image> images;
    public DataSeparater(List<Image> images) {
        this.images = images;
    }
    public List<Integer> getTestLabels() {
        return getLabels(images);
    }

    public List<double[]> getTestDataNoLabels() {
        List<double[]> retList = new ArrayList<>();
        for(Image i:images){
            retList.add(i.getData());
        }
        return retList;
    }

    private List<Integer> getLabels(List<Image> images){
        List<Integer> labels = new ArrayList<>();
        for(Image image:images){
            labels.add(image.getLabel());
        }
        return labels;
    }
}
