package rqg.fantasy.newchart.chart.bhr;

import android.content.Context;
import android.util.AttributeSet;

import rqg.fantasy.newchart.chart.BaseBarChart;
import rqg.fantasy.newchart.chart.render.BarXAxisRender;
import rqg.fantasy.newchart.chart.render.BaseBarDashLineContentRender;

/**
 * *Created by rqg on 5/4/16.
 */
public class BhrHeartChart extends BaseBarChart<BhrHeartData> {

    public BhrHeartChart(Context context) {
        this(context, null);
    }

    public BhrHeartChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BhrHeartChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected BaseBarDashLineContentRender<BhrHeartData> getContentRender(int maxWidth) {
        return new BhrContentRender(maxWidth);
    }

    @Override
    protected BarXAxisRender<BhrHeartData> getBarXAxisRender() {
        return new BhrXAxisRender();
    }

}
