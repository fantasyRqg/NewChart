package rqg.fantasy.newchart.chart;

import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * *Created by rqg on 4/27/16.
 */
public interface ChartRender {

    void draw(Canvas canvas);

    void onSizeChange(int left, int top, int right, int bottom);

    RectF getSelfBounds();

}
