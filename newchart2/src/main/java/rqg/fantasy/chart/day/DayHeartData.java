package rqg.fantasy.chart.day;

import java.util.Collections;

import rqg.fantasy.chart.ChartData;

/**
 * *Created by rqg on 5/3/16.
 */
public class DayHeartData extends ChartData<Integer> {

    @Override
    public int maxYValue() {
        if (isEmpty())
            return 0;
        else {
            return Collections.max(yValueList);
        }
    }
}
