package rqg.fantasy.newchart.chart.day;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.rqg.common.util.DisplayUtil;

import rqg.fantasy.newchart.chart.ChartRender;
import rqg.fantasy.newchart.chart.render.XAxisRender;
import rqg.fantasy.newchart.chart.render.YAxisRender;

/**
 * *Created by rqg on 4/27/16.
 */
public class DayHeartChart extends View {
    protected ChartRender mIndicatorRender;

    protected DayHeartContentRender mContentRender;
    protected XAxisRender mXAxisRender;
    protected YAxisRender mYAxisRender;


    protected int mTopPadding;

    protected DayHeartData mDayHeartData;


    public DayHeartChart(Context context) {
        this(context, null);
    }

    public DayHeartChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DayHeartChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mTopPadding = (int) DisplayUtil.dp2Px(getContext(), 30);
        mYAxisRender = new YAxisRender();
        mXAxisRender = new XAxisRender();
        mContentRender = new DayHeartContentRender();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        mYAxisRender.onSizeChange(0, mTopPadding, getMeasuredWidth(), getMeasuredHeight());


        mXAxisRender.onSizeChange((int) mYAxisRender.getSelfBounds().right, mTopPadding, getMeasuredWidth(), getMeasuredHeight());

        mContentRender.onSizeChange((int) mYAxisRender.getSelfBounds().right, mTopPadding, getMeasuredWidth(), (int) mXAxisRender.getSelfBounds().bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mYAxisRender.draw(canvas);
        mXAxisRender.draw(canvas);
        mContentRender.draw(canvas);
    }


    public YAxisRender getYAxisRender() {
        return mYAxisRender;
    }

    public void setYAxisRender(YAxisRender YAxisRender) {
        mYAxisRender = YAxisRender;
    }
//
//
//    public DayHeartData getDayHeartData() {
//        return mDayHeartData;
//    }

    public void setDayHeartData(DayHeartData dayHeartData) {
        mDayHeartData = dayHeartData;
        mYAxisRender.setMaxYValue(mDayHeartData.maxYValue());
    }
}
