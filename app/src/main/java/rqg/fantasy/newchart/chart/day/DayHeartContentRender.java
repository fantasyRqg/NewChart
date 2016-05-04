package rqg.fantasy.newchart.chart.day;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;

import rqg.fantasy.newchart.chart.render.BaseBarContentRender;

/**
 * *Created by rqg on 5/3/16.
 */
public class DayHeartContentRender extends BaseBarContentRender<DayHeartData> {

    protected Paint mContentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    protected int mBarColor = Color.parseColor("#4CFFFFFF");
    protected int mSelectedColor = Color.parseColor("#99FFFFFF");

    public DayHeartContentRender(int maxWidth) {
        super(maxWidth);

        initPaint();
    }


    @Override
    public void draw(Canvas canvas) {

        if (mBarBoundsArray == null)
            return;
        for (int i = 0; i < mBarBoundsArray.length; i++) {
            RectF b = mBarBoundsArray[i];
            if (i == mSelectedIndex) {
                mContentPaint.setColor(mSelectedColor);
                canvas.drawRect(b, mContentPaint);
                mContentPaint.setColor(mBarColor);
            } else {
                canvas.drawRect(b, mContentPaint);
            }
        }

        mDashLineRender.draw(canvas);
    }


    protected void initPaint() {
        mContentPaint.setColor(mBarColor);
    }

    @Override
    protected void initBarBounds() {
        if (mChartData == null || mChartData.isEmpty()) {
            mBarBoundsArray = null;
            onBarBoundsChange();
            mDashLineRender.emptyPath();
            return;
        }

        int height = (int) mSelfBounds.height();

        if (height == 0)
            return;


        ArrayList<Integer> yList = mChartData.getyValueList();
        int size = yList.size();

        float cellWidth = mSelfBounds.width() / size;
        float width = cellWidth * 0.8f;

        if (width > mMaxWidth) {
            width = mMaxWidth;
        }

        float tmpLeft = mSelfBounds.left + (cellWidth - width) / 2f;


        float maxValue = mChartData.maxYValue();

        mBarBoundsArray = new RectF[size];


        RectF tmp = new RectF(tmpLeft, mSelfBounds.top, tmpLeft + width, mSelfBounds.bottom);

        for (int i = 0; i < mBarBoundsArray.length; i++) {
            int v = yList.get(i);
            RectF bounds = new RectF(tmp);
            bounds.top = bounds.bottom - v / maxValue * height;
            tmp.offset(cellWidth, 0);
            mBarBoundsArray[i] = bounds;

        }

        mDashLineRender.computePath(mBarBoundsArray);

        onBarBoundsChange();
    }
}
