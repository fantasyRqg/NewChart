package rqg.fantasy.newchart.chart.render;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import rqg.fantasy.newchart.chart.ChartRender;


/**
 * *Created by rqg on 5/3/16.
 */
public class BarXAxisRender implements ChartRender {

    protected RectF mBounds = new RectF();
    protected RectF mSelfBounds = new RectF();


    private Path mXAxisLine = new Path();

    public BarXAxisRender() {

        initPaint();

        mSelfBounds.set(mSelfBounds.left
                , mSelfBounds.top,
                mSelfBounds.right
                , mSelfBounds.top + TEXT_PADDING_BOTTOM + TEXT_PADDING_TOP + mValuePaint.getTextSize());
    }

    private static final int TEXT_PADDING_BOTTOM = 50;
    private static final int TEXT_PADDING_TOP = 15;

    private Paint mValuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    private void initPaint() {
        mValuePaint.setTextSize(25);
        mValuePaint.setStrokeWidth(10);
        mValuePaint.setColor(Color.WHITE);
        mValuePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void draw(Canvas canvas) {
        if (mXAxisLine != null) {
            canvas.drawPath(mXAxisLine, mValuePaint);
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
            mXAxisLine.moveTo(rf.left, rf.bottom);
            mXAxisLine.lineTo(rf.right, rf.bottom);
        }

    }

    @Override
    public RectF getSelfBounds() {
        return mSelfBounds;
    }
}
