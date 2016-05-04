package rqg.fantasy.newchart.chart.render;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import rqg.fantasy.newchart.chart.ChartRender;


/**
 * *Created by rqg on 5/3/16.
 */
public class XAxisRender implements ChartRender {

    protected RectF mBounds = new RectF();
    protected RectF mSelfBounds = new RectF();
    protected int mMaxBarWidth;
    protected int mDataSize = 0;


    public XAxisRender(int maxBarWidth) {
        mMaxBarWidth = maxBarWidth;

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
    }

    @Override
    public void draw(Canvas canvas) {
    }

    @Override
    public void onSizeChange(int left, int top, int right, int bottom) {
        mBounds.set(left, top, right, bottom);

        mSelfBounds.set(mBounds.left, mBounds.bottom - mSelfBounds.height(), mBounds.right, mBounds.bottom);
    }


    public void setDataSize(int dataSize) {
        mDataSize = dataSize;
    }

    @Override
    public RectF getSelfBounds() {
        return mSelfBounds;
    }
}
