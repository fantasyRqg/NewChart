package rqg.fantasy.chart.bhr;

import java.util.Collections;
import java.util.Comparator;

import rqg.fantasy.chart.ChartData;

/**
 * *Created by rqg on 5/4/16.
 */
public class BhrHeartData extends ChartData<BhrData> {
    @Override
    public int maxYValue() {
        if (isEmpty())
            return 0;

        return Collections.max(yValueList, new Comparator<BhrData>() {
            @Override
            public int compare(BhrData lhs, BhrData rhs) {
                return lhs.maxValue() - rhs.maxValue();
            }
        }).maxValue();
    }
}
