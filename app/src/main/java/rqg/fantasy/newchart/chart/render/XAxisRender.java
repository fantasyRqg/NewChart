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

    private static final int TEXT_PADDING_BOTTOM = 50;
    private static final int TEXT_PADDING_TOP = 15;

    private Paint mValuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public XAxisRender() {

        initPaint();

        mSelfBounds.set(mSelfBounds.left
                , mSelfBounds.top,
                mSelfBounds.right
                , mSelfBounds.top + TEXT_PADDING_BOTTOM + TEXT_PADDING_TOP + mValuePaint.getTextSize());
    }


    private void initPaint() {
        mValuePaint.setTextSize(25);
    }

    @Override
    public void draw(Canvas canvas) {
    }

    @Override
    public void onSizeChange(int left, int top, int right, int bottom) {
        mBounds.set(left, top, right, bottom);

        mSelfBounds.set(mBounds.left, mBounds.bottom - mSelfBounds.height(), mBounds.right, mBounds.bottom);
    }

    @Override
    public RectF getSelfBounds() {
        return mSelfBounds;
    }
}
