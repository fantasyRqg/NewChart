package rqg.fantasy.newchart.chart;

/**
 * *Created by rqg on 5/3/16.
 */
public class ChartPoint {
    public float x;
    public float y;

    public ChartPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "x:" + x + " , y:" + y;
    }
}
