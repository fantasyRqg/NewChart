package rqg.fantasy.newchart.chart.bhr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.rqg.common.util.DisplayUtil;

import rqg.fantasy.newchart.chart.MarkerView;
import rqg.fantasy.newchart.chart.day.DayHeartContentRender;
import rqg.fantasy.newchart.chart.day.DayHeartXAxisRender;
import rqg.fantasy.newchart.chart.render.BarXAxisRender;
import rqg.fantasy.newchart.chart.render.BaseBarContentRender;
import rqg.fantasy.newchart.chart.render.IndicatorRender;
import rqg.fantasy.newchart.chart.render.YAxisRender;

/**
 * *Created by rqg on 5/4/16.
 */
public class BhrHeartChart extends View {

    protected IndicatorRender mIndicatorRender;

    protected BaseBarContentRender mContentRender;
    protected BarXAxisRender mBarXAxisRender;
    protected YAxisRender mYAxisRender;


    protected int mTopPadding;

    protected BhrHeartData mBhrHeartData;


    public BhrHeartChart(Context context) {
        this(context, null);
    }

    public BhrHeartChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BhrHeartChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initRender();
    }

    private void initRender() {

        int maxWidth = (int) DisplayUtil.dp2Px(getContext(), 20);
        mTopPadding = (int) DisplayUtil.dp2Px(getContext(), 30);
        mYAxisRender = new YAxisRender();
        mBarXAxisRender = new DayHeartXAxisRender();
        mContentRender = new BhrContentRender(maxWidth);
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

    public void setBhrHeartData(BhrHeartData bhrHeartData) {
        mBhrHeartData = bhrHeartData;
        mYAxisRender.setMaxYValue(mBhrHeartData.maxYValue());
        mContentRender.setDayHeartData(mBhrHeartData);

        invalidate();
    }


    public void setMarkerView(MarkerView markerView) {
        mIndicatorRender.setMarkerView(markerView);
    }
}
