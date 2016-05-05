package rqg.fantasy.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.rqg.common.util.DisplayUtil;

import rqg.fantasy.chart.day.DayHeartContentRender;
import rqg.fantasy.chart.render.BarXAxisRender;
import rqg.fantasy.chart.render.BaseBarDashLineContentRender;
import rqg.fantasy.chart.render.IndicatorRender;
import rqg.fantasy.chart.render.YAxisRender;

/**
 * *Created by rqg on 5/4/16.
 */
public abstract class BaseBarChart<T extends ChartData> extends View {


    protected IndicatorRender mIndicatorRender;

    protected BaseBarDashLineContentRender<T> mContentRender;
    protected BarXAxisRender<T> mBarXAxisRender;
    protected YAxisRender mYAxisRender;


    protected int mTopPadding;

    protected T mDayHeartData;


    public BaseBarChart(Context context) {
        this(context, null);
    }

    public BaseBarChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseBarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initRender();
    }


    private void initRender() {

        int maxWidth = (int) DisplayUtil.dp2Px(getContext(), 20);
        mTopPadding = (int) DisplayUtil.dp2Px(getContext(), 30);
        mYAxisRender = new YAxisRender();
        mBarXAxisRender = getBarXAxisRender();
        mContentRender = getContentRender(maxWidth);
        mIndicatorRender = new IndicatorRender();

        mContentRender.setBarBoundsChangeListener(new DayHeartContentRender.BarBoundsChange() {
            @Override
            public void onBarBoundsChange(RectF[] barBounds) {
                mBarXAxisRender.setBarBoundsArray(barBounds);
            }

            @Override
            public void onBarSelected(RectF rectF, int index) {
                mIndicatorRender.setSelectedBounds(rectF, index);
            }
        });
    }


    protected abstract BaseBarDashLineContentRender<T> getContentRender(int maxWidth);

    protected abstract BarXAxisRender<T> getBarXAxisRender();

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mBarXAxisRender.onSizeChange(getPaddingLeft() + (int) mYAxisRender.getSelfBounds().right, mTopPadding, getMeasuredWidth() - getPaddingRight(), getMeasuredHeight());

        mYAxisRender.onSizeChange(getPaddingLeft(), mTopPadding, getMeasuredWidth() - getPaddingRight(), (int) mBarXAxisRender.getSelfBounds().top);


        mContentRender.onSizeChange(getPaddingLeft() + (int) mYAxisRender.getSelfBounds().right, mTopPadding, getMeasuredWidth() - getPaddingRight(), (int) mBarXAxisRender.getSelfBounds().top);

        mIndicatorRender.onSizeChange(getPaddingLeft(), 0, getMeasuredWidth() - getPaddingRight(), getMeasuredHeight());
        mIndicatorRender.setTopLine(mYAxisRender.getSelfBounds().top);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mYAxisRender.draw(canvas);
        mBarXAxisRender.draw(canvas);
        mContentRender.draw(canvas);
        mIndicatorRender.draw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mContentRender.onTouch(event.getX(), event.getY())) {
            invalidate();
        }
        return true;
    }

    public void setChartData(T chartData) {
        mDayHeartData = chartData;
        mYAxisRender.setMaxYValue(mDayHeartData.maxYValue());
        mContentRender.setDayHeartData(mDayHeartData);
        mBarXAxisRender.setChartData(chartData);

        invalidate();
    }


    public void setMarkerView(MarkerView markerView) {
        mIndicatorRender.setMarkerView(markerView);
    }
}
