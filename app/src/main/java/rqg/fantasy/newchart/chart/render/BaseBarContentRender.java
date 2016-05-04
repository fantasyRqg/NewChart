package rqg.fantasy.newchart.chart.render;

import android.graphics.RectF;

import rqg.fantasy.newchart.chart.ChartData;
import rqg.fantasy.newchart.chart.ChartRender;

/**
 * *Created by rqg on 5/4/16.
 */
abstract public class BaseBarContentRender<T extends ChartData> implements ChartRender {

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


    public BaseBarContentRender(int maxWidth) {

        mMaxWidth = maxWidth;

        mDashLineRender = new DashLineRender();
    }


    public void setDayHeartData(T chartData) {

        mChartData = chartData;
        setSelectedIndex(-1);
        initBarBounds();
    }

    abstract protected void initBarBounds();


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

        int index = (int) ((x - left) / cellWidth);

        return setSelectedIndex(index);
    }


    protected void onBarBoundsChange() {
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
