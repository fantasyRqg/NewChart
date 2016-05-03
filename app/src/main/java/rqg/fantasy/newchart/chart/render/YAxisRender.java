package rqg.fantasy.newchart.chart.render;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;

import rqg.fantasy.newchart.chart.ChartRender;

/**
 * *Created by rqg on 4/27/16.
 */
public class YAxisRender implements ChartRender {


    private RectF mSelfBounds = new RectF();
    private int mYStep = 0;
    private Paint mValuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mDashPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private RectF mBounds = new RectF();

    private float mVerticalOffset = 0;
    private Path mPath = new Path();
    private static final int TEXT_PADDING_LEFT = 10;


    private int mDashLineColor = Color.RED;
    private int mValueColor = Color.BLUE;

    public YAxisRender() {
        initPaint();

        mSelfBounds.set(mSelfBounds.left
                , mSelfBounds.top
                , mSelfBounds.left + TEXT_PADDING_LEFT + mValuePaint.getTextSize() * 4
                , mSelfBounds.bottom);
    }


    private void initPaint() {
        mValuePaint.setTextSize(30);
        mValuePaint.setTextAlign(Paint.Align.LEFT);
        mValuePaint.setColor(mValueColor);
        PathEffect pe = new DashPathEffect(new float[]{10, 3}, 0);

        mDashPaint.setPathEffect(pe);
        mDashPaint.setStyle(Paint.Style.STROKE);
        mDashPaint.setStrokeWidth(1);
        mDashPaint.setColor(mDashLineColor);
    }

    @Override
    public void draw(Canvas canvas) {
        float left = mBounds.left;
        float top = mBounds.top;
        float right = mBounds.right;
        int max = mYStep * 3;
        int count = 3;
        while (count-- > 0) {
            mPath.reset();
            mPath.moveTo(left, top);
            mPath.lineTo(right, top);
            canvas.drawPath(mPath, mDashPaint);


            canvas.drawText(String.valueOf(max), left + TEXT_PADDING_LEFT, top + mValuePaint.getTextSize(), mValuePaint);

            top += mVerticalOffset;
            max -= mYStep;
        }
    }

    public void setMaxYValue(int max) {
        int average = max / 3;

        if (average % 10 == 0) {
            mYStep = average;
        } else {
            mYStep = (average / 10 + 1) * 10;
        }
    }

    @Override
    public void onSizeChange(int left, int top, int right, int bottom) {
        mBounds.set(left, top, right, bottom);

        mVerticalOffset = mBounds.height() / 3f;
        mPath.reset();
        mPath.moveTo(mBounds.left, mBounds.top);
        mPath.lineTo(mBounds.right, mBounds.top);

        float swidth = mSelfBounds.width();
        mSelfBounds.set(mBounds.left, mBounds.top, mBounds.left + swidth, mBounds.bottom);
    }


    @Override
    public RectF getSelfBounds() {
        return mSelfBounds;
    }
}
