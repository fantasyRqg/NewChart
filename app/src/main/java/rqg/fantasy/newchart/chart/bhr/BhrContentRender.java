package rqg.fantasy.newchart.chart.bhr;

import android.graphics.Canvas;
import android.graphics.Paint;

import rqg.fantasy.newchart.chart.render.BaseBarContentRender;

/**
 * *Created by rqg on 5/4/16.
 */
public class BhrContentRender extends BaseBarContentRender<BhrHeartData> {

    protected Paint mContentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    
    public BhrContentRender(int maxWidth) {
        super(maxWidth);
    }

    @Override
    protected void initBarBounds() {

    }

    @Override
    public void draw(Canvas canvas) {

    }
}
