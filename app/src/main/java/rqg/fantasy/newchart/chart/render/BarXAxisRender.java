package rqg.fantasy.newchart.chart.render;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import rqg.fantasy.newchart.chart.ChartData;
import rqg.fantasy.newchart.chart.ChartRender;


/**
 * *Created by rqg on 5/3/16.
 */
public class BarXAxisRender<T extends ChartData> implements ChartRender {

    protected RectF mBounds = new RectF();
    protected RectF mSelfBounds = new RectF();
    protected T mChartData;

    protected Path mXAxisLine = new Path();
    protected static final int TEXT_PADDING_BOTTOM = 50;
    protected static final int TEXT_PADDING_TOP = 15;

    protected Paint mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public BarXAxisRender() {

        initPaint();

        mSelfBounds.set(mSelfBounds.left
                , mSelfBounds.top,
                mSelfBounds.right
                , mSelfBounds.top + TEXT_PADDING_BOTTOM + TEXT_PADDING_TOP + mLinePaint.getTextSize());
    }


    private void initPaint() {
        mLinePaint.setStrokeWidth(2);
        mLinePaint.setColor(Color.WHITE);
        mLinePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void draw(Canvas canvas) {
        if (mXAxisLine != null) {
            canvas.drawPath(mXAxisLine, mLinePaint);
        }
    }

    @Override
    public void onSizeChange(int left, int top, int right, int bottom) {
        mBounds.set(left, top, right, bottom);

        mSelfBounds.set(mBounds.left, mBounds.bottom - mSelfBounds.height(), mBounds.right, mBounds.bottom);
    }


    public void setBarBoundsArray(RectF[] barBoundsArray) {
        mXAxisLine.reset();

        if (barBoundsArray == null)
            return;
        for (RectF rf : barBoundsArray) {
            mXAxisLine.moveTo(rf.left, mSelfBounds.top);
            mXAxisLine.lineTo(rf.right, mSelfBounds.top);
        }

    }

    public void setChartData(T chartData) {
        mChartData = chartData;
    }

    @Override
    public RectF getSelfBounds() {
        return mSelfBounds;
    }
}
