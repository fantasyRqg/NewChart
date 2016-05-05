package rqg.fantasy.newchart.chart.bhr;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;

import rqg.fantasy.newchart.chart.render.BarXAxisRender;

/**
 * *Created by rqg on 5/5/16.
 */
public class BhrXAxisRender extends BarXAxisRender<BhrHeartData> {

    protected Paint mValuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    protected RectF[] mBarBoundsArray;

    public BhrXAxisRender() {
        super();

        initPaint();
    }

    protected void initPaint() {
        mValuePaint.setTextSize(20);
        mValuePaint.setColor(Color.WHITE);
        mValuePaint.setTextAlign(Paint.Align.CENTER);
    }


    @Override
    public void setBarBoundsArray(RectF[] barBoundsArray) {
        super.setBarBoundsArray(barBoundsArray);
        mBarBoundsArray = barBoundsArray;
    }

    @Override
    public void onSizeChange(int left, int top, int right, int bottom) {
        super.onSizeChange(left, top, right, bottom);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);


        float y = mSelfBounds.top + TEXT_PADDING_TOP + mValuePaint.getTextSize();
        ArrayList<String> labelList = mChartData.getxLabelList();
        if (labelList != null && mBarBoundsArray != null) {
            int i = 0;

            while (labelList.size() > i && mBarBoundsArray.length > i) {
                canvas.drawText(labelList.get(i), mBarBoundsArray[i].centerX(), y, mValuePaint);
                i++;
            }

        }
    }
}
