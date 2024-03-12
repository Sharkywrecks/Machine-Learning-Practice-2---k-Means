package data;
/**
 * Image class
 * IGNORE!
 * */
public class Image {
    private double[] data;
    private int label;

    public Image(double[] data, int label) {
        this.data = data;
        this.label = label;
    }

    public double[] getData() {
        return data;
    }

    public int getLabel() {
        return label;
    }
}
