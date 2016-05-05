package rqg.fantasy.chart.day;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import rqg.fantasy.chart.render.BarXAxisRender;

/**
 * *Created by rqg on 5/4/16.
 */
public class DayHeartXAxisRender extends BarXAxisRender<DayHeartData> {

    protected Paint mValuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    protected float mLeftBound, mRightBound;

    public DayHeartXAxisRender() {
        super();

        initPaint();
    }

    protected void initPaint() {
        mValuePaint.setTextSize(20);
        mValuePaint.setColor(Color.WHITE);
    }


    @Override
    public void setBarBoundsArray(RectF[] barBoundsArray) {
        super.setBarBoundsArray(barBoundsArray);
        if (barBoundsArray != null && barBoundsArray.length >= 3) {
            mLeftBound = barBoundsArray[0].left;
            mRightBound = barBoundsArray[barBoundsArray.length - 1].right;
        }
    }

    @Override
    public void onSizeChange(int left, int top, int right, int bottom) {
        super.onSizeChange(left, top, right, bottom);
        mLeftBound = left;
        mRightBound = right;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);


        float y = mSelfBounds.top + TEXT_PADDING_TOP + mValuePaint.getTextSize();

        mValuePaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("00:00", mLeftBound, y, mValuePaint);
        mValuePaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("12:00", (mLeftBound + mRightBound) / 2f, y, mValuePaint);
        mValuePaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("23:59", mRightBound, y, mValuePaint);
    }
}
