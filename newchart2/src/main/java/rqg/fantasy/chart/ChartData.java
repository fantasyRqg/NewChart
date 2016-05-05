package rqg.fantasy.chart;

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
        if (yValueList == null || yValueList.size() <= i || i < 0) {
            return null;
        }
        return yValueList.get(i);
    }

    public abstract int maxYValue();


    public ArrayList<T> getyValueList() {
        return yValueList;
    }

    public void setyValueList(ArrayList<T> yValueList) {
        this.yValueList = yValueList;
    }

    public ArrayList<String> getxLabelList() {
        return xLableList;
    }


    public void setxLableList(ArrayList<String> xLableList) {
        this.xLableList = xLableList;
    }


    public boolean isEmpty() {
        return yValueList == null || yValueList.isEmpty();
    }
}
