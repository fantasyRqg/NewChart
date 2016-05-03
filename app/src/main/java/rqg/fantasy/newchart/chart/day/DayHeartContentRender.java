package rqg.fantasy.newchart.chart.day;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import rqg.fantasy.newchart.chart.ChartRender;

/**
 * *Created by rqg on 5/3/16.
 */
public class DayHeartContentRender implements ChartRender {
    private static final String TAG = "DayHeartContentRender";
    protected DayHeartData mDayHeartData;
    protected RectF mSelfBounds = new RectF();
    private int mMaxWidth = 0;


    private RectF[] mBarBoundsArray;

    private Paint mContentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public DayHeartContentRender(int maxWidth) {
        initPaint();

        mMaxWidth = maxWidth;
    }

    private void initPaint() {
        mContentPaint.setColor(Color.parseColor("#3c123a12"));
    }

    @Override
    public void draw(Canvas canvas) {

        if (mBarBoundsArray == null)
            return;
        for (RectF b : mBarBoundsArray) {
            Log.d(TAG, "draw: " + b);
            canvas.drawRect(b, mContentPaint);
        }
    }

    @Override
    public void onSizeChange(int left, int top, int right, int bottom) {
        mSelfBounds.set(left, top, right, bottom);

        setDayHeartData(mDayHeartData);
    }

    @Override
    public RectF getSelfBounds() {
        return mSelfBounds;
    }

    public void setDayHeartData(DayHeartData dayHeartData) {
        mDayHeartData = dayHeartData;

        initBarBounds();

    }


    private void initBarBounds() {
        int height = (int) mSelfBounds.height();

        if (height == 0)
            return;


        ArrayList<Integer> yList = mDayHeartData.getyValueList();
        int size = yList.size();

        float cellWidth = mSelfBounds.width() / size;
        float width = cellWidth * 0.8f;

        if (width > mMaxWidth) {
            width = mMaxWidth;
        }

        float tmpLeft = mSelfBounds.left + (cellWidth - width) / 2f;


        float maxValue = Collections.max(yList);

        mBarBoundsArray = new RectF[size];


        RectF tmp = new RectF(tmpLeft, mSelfBounds.top, tmpLeft + width, mSelfBounds.bottom);

        for (int i = 0; i < mBarBoundsArray.length; i++) {
            int v = yList.get(i);
            RectF bounds = new RectF(tmp);
            bounds.top = bounds.bottom - v / maxValue * height;


            tmp.offset(cellWidth, 0);
            mBarBoundsArray[i] = bounds;
        }
    }
}
