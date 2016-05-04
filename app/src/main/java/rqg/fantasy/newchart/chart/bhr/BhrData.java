package rqg.fantasy.newchart.chart.bhr;

/**
 * *Created by rqg on 5/4/16.
 */
public class BhrData {
    public int max, min, bhr;

    public int maxValue() {
        int maxValue = Integer.MIN_VALUE;
        if (min > maxValue)
            maxValue = min;
        if (bhr > maxValue)
            maxValue = bhr;
        if (max > maxValue)
            maxValue = max;

        return maxValue;
    }
}
