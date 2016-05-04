package rqg.fantasy.newchart.chart.day;

import android.content.Context;
import android.util.AttributeSet;

import rqg.fantasy.newchart.chart.BaseBarChart;
import rqg.fantasy.newchart.chart.render.BarXAxisRender;
import rqg.fantasy.newchart.chart.render.BaseBarContentRender;

/**
 * *Created by rqg on 4/27/16.
 */
public class DayHeartChart extends BaseBarChart<DayHeartData> {


    public DayHeartChart(Context context) {
        this(context, null);
    }

    public DayHeartChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DayHeartChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    protected BaseBarContentRender<DayHeartData> getContentRender(int maxWidth) {
        return new DayHeartContentRender(maxWidth);
    }

    @Override
    protected BarXAxisRender getBarXAxisRender() {
        return new DayHeartXAxisRender();
    }
}
