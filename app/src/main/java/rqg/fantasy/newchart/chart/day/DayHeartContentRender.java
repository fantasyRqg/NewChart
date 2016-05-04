package rqg.fantasy.newchart.chart.day;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;

import rqg.fantasy.newchart.chart.ChartRender;

/**
 * *Created by rqg on 5/3/16.
 */
public class DayHeartContentRender implements ChartRender {
    protected DayHeartData mDayHeartData;
    protected RectF mSelfBounds = new RectF();
    private int mMaxWidth = 0;

    private DashLineRender mDashLineRender;


    private RectF[] mBarBoundsArray;

    private Paint mContentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private BarBoundsChange mBarBoundsChangeListener;

    private int mBarColor = Color.parseColor("#4CFFFFFF");
    private int mSelectedColor = Color.parseColor("#99FFFFFF");
    private int mSelectedIndex = -1;

    public DayHeartContentRender(int maxWidth) {
        initPaint();

        mMaxWidth = maxWidth;

        mDashLineRender = new DashLineRender();
    }

    private void initPaint() {
        mContentPaint.setColor(mBarColor);
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
        setSelectedIndex(-1);
        initBarBounds();

    }


    private void initBarBounds() {
        if (mDayHeartData == null || mDayHeartData.isEmpty()) {
            mBarBoundsArray = null;
            onBarBoundsChange();
            mDashLineRender.emptyPath();
            return;
        }

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


        float maxValue = mDayHeartData.maxYValue();

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

    private void onBarBoundsChange() {
        if (mBarBoundsChangeListener != null) {
            mBarBoundsChangeListener.onBarBoundsChange(mBarBoundsArray);
        }
    }


    public int getSelectedIndex() {
        return mSelectedIndex;
    }

    public boolean setSelectedIndex(int selectedIndex) {
        if (mSelectedIndex == selectedIndex) {
            return false;
        }

        mSelectedIndex = selectedIndex;

        onBarSelected(mSelectedIndex);

        return true;
    }


    private void onBarSelected(int index) {
        RectF[] bounds = mBarBoundsArray;

        if (mBarBoundsChangeListener != null) {
            RectF rf = null;

            if (index >= 0 && index < mBarBoundsArray.length) {
                rf = bounds[index];
            }
            mBarBoundsChangeListener.onBarSelected(rf, index);

        }
    }

    public boolean onTouch(float x, float y) {
        RectF[] bounds = mBarBoundsArray;
        if (bounds == null)
            return setSelectedIndex(-1);

        float left = mBarBoundsArray[0].left;
        float right = mBarBoundsArray[mBarBoundsArray.length - 1].right;

        float cellWidth = (right - left) / mBarBoundsArray.length;

        int index = (int) ((x - left) / cellWidth);

        return setSelectedIndex(index);
    }

    public void setBarBoundsChangeListener(BarBoundsChange barBoundsChangeListener) {
        mBarBoundsChangeListener = barBoundsChangeListener;
    }

    public static interface BarBoundsChange {
        void onBarBoundsChange(RectF[] barBounds);

        void onBarSelected(RectF rectF, int index);
    }

}
