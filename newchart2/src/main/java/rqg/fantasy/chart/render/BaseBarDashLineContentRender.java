package rqg.fantasy.chart.render;

import android.graphics.RectF;

import rqg.fantasy.chart.ChartData;
import rqg.fantasy.chart.ChartRender;

/**
 * *Created by rqg on 5/4/16.
 */
abstract public class BaseBarDashLineContentRender<T extends ChartData> implements ChartRender {

    protected T mChartData;


    protected RectF mSelfBounds = new RectF();
    protected int mMaxWidth = 0;

    protected DashLineRender mDashLineRender;


    protected RectF[] mBarBoundsArray;


    protected BarBoundsChange mBarBoundsChangeListener;

    protected int mSelectedIndex = -1;

    public void setBarBoundsChangeListener(BarBoundsChange barBoundsChangeListener) {
        mBarBoundsChangeListener = barBoundsChangeListener;
    }


    public BaseBarDashLineContentRender(int maxWidth) {

        mMaxWidth = maxWidth;

        mDashLineRender = new DashLineRender();
    }


    public void setDayHeartData(T chartData) {

        mChartData = chartData;
        setSelectedIndex(-1);

        if (mChartData == null || mChartData.isEmpty()) {
            mBarBoundsArray = null;
            onBarBoundsChange();
            mDashLineRender.emptyPath();
            return;
        }

        initBarBoundsAndDashLine();

        onBarBoundsChange();
    }

    abstract protected void initBarBoundsAndDashLine();


    public static interface BarBoundsChange {
        void onBarBoundsChange(RectF[] barBounds);

        void onBarSelected(RectF rectF, int index);
    }


    public boolean onTouch(float x, float y) {
        RectF[] bounds = mBarBoundsArray;
        if (bounds == null)
            return setSelectedIndex(-1);

        float left = mBarBoundsArray[0].left;
        float right = mBarBoundsArray[mBarBoundsArray.length - 1].right;

        float cellWidth = (right - left) / mBarBoundsArray.length;

        float fi = ((x - left) / cellWidth);

        int index = fi < 0f ? -1 : (int) fi;

//        Log.d(TAG, "onTouch() called with: " + "x = [" + x + "], index = [" + index + "], left = [" + left + "], cellWidth = [" + cellWidth + "]");

        return setSelectedIndex(index);
    }


    protected void onBarBoundsChange() {
        if (mBarBoundsChangeListener != null) {
            mBarBoundsChangeListener.onBarBoundsChange(mBarBoundsArray);
        }
    }


    protected float getBarWidth(float cellWidth) {
        float width = cellWidth * 0.8f;

        if (width > mMaxWidth) {
            width = mMaxWidth;
        }

        return width;
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


    protected void onBarSelected(int index) {
        RectF[] bounds = mBarBoundsArray;

        if (mBarBoundsChangeListener != null) {
            RectF rf = null;

            if (index >= 0 && index < mBarBoundsArray.length) {
                rf = bounds[index];
            }
            mBarBoundsChangeListener.onBarSelected(rf, index);

        }
    }

    @Override
    public void onSizeChange(int left, int top, int right, int bottom) {
        mSelfBounds.set(left, top, right, bottom);

        setDayHeartData(mChartData);
    }

    @Override
    public RectF getSelfBounds() {
        return mSelfBounds;
    }

}
