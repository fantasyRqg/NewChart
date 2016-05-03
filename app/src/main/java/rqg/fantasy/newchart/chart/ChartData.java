package rqg.fantasy.newchart.chart;

import java.util.ArrayList;

/**
 * *Created by rqg on 4/27/16.
 */
abstract public class ChartData<T> {

    protected ArrayList<T> yValueList;
    protected ArrayList<String> xLableList;


    /**
     * @param i x index
     * @return y string value
     */
    public String getYText(int i) {
        return String.valueOf(yValueList.get(i));
    }

    public T getY(int i) {
        return yValueList.get(i);
    }

    public abstract int maxYValue();


    public ArrayList<T> getyValueList() {
        return yValueList;
    }

    public void setyValueList(ArrayList<T> yValueList) {
        this.yValueList = yValueList;
    }

    public ArrayList<String> getxLableList() {
        return xLableList;
    }

    public void setxLableList(ArrayList<String> xLableList) {
        this.xLableList = xLableList;
    }
}
