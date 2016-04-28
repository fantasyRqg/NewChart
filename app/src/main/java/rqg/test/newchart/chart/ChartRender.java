package rqg.test.newchart.chart;

import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * *Created by rqg on 4/27/16.
 */
public interface ChartRender {
    void draw(Canvas canvas);

    void onSizeChange(int width, int height);

    RectF getSelfBounds();
}
