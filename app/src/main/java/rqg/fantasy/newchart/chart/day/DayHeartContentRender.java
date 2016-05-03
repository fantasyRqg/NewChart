package rqg.fantasy.newchart.chart.day;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import rqg.fantasy.newchart.chart.ChartRender;

/**
 * *Created by rqg on 5/3/16.
 */
public class DayHeartContentRender implements ChartRender {
    protected DayHeartData mDayHeartData;
    protected RectF mSelfBounds = new RectF();

    private Paint mContentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public DayHeartContentRender() {
        initPaint();
    }

    private void initPaint() {
        mContentPaint.setColor(Color.GREEN);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(mSelfBounds, mContentPaint);
    }

    @Override
    public void onSizeChange(int left, int top, int right, int bottom) {
        mSelfBounds.set(left, top, right, bottom);
    }

    @Override
    public RectF getSelfBounds() {
        return mSelfBounds;
    }

    public void setDayHeartData(DayHeartData dayHeartData) {
        mDayHeartData = dayHeartData;
    }
}
