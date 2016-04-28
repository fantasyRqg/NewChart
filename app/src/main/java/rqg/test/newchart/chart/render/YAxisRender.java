package rqg.test.newchart.chart.render;

import android.graphics.Canvas;
import android.graphics.RectF;

import rqg.test.newchart.chart.ChartRender;

/**
 * *Created by rqg on 4/27/16.
 */
public class YAxisRender implements ChartRender {

    int mYStep = 0;

    public YAxisRender(int max) {
        int average = max / 3;

        if (average % 10 == 0) {
            mYStep = average;
        } else {
            mYStep = (average / 10 + 1) * 10;
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void onSizeChange(int width, int height) {

    }

    @Override
    public RectF getSelfBounds() {
        return null;
    }
}
