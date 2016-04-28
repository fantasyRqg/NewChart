package rqg.test.newchart.chart;

import java.util.ArrayList;

/**
 * *Created by rqg on 4/27/16.
 */
public class ChartData<T> {

    public ArrayList<T> yValueList;
    public ArrayList<String> xLableList;


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
}
