package rqg.fantasy.newchart.chart.day;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.rqg.common.util.DisplayUtil;

import rqg.fantasy.newchart.chart.ChartRender;
import rqg.fantasy.newchart.chart.render.BarXAxisRender;
import rqg.fantasy.newchart.chart.render.YAxisRender;

/**
 * *Created by rqg on 4/27/16.
 */
public class DayHeartChart extends View {
    private static final String TAG = "DayHeartChart";


    protected ChartRender mIndicatorRender;

    protected DayHeartContentRender mContentRender;
    protected BarXAxisRender mBarXAxisRender;
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


        int maxWidth = (int) DisplayUtil.dp2Px(getContext(), 10);
        mTopPadding = (int) DisplayUtil.dp2Px(getContext(), 30);
        mYAxisRender = new YAxisRender();
        mBarXAxisRender = new BarXAxisRender();
        mContentRender = new DayHeartContentRender(maxWidth);

        mContentRender.setBarBoundsChangeListener(new DayHeartContentRender.BarBoundsChange() {
            @Override
            public void onBarBoundsChange(RectF[] barBounds) {
                mBarXAxisRender.setBarBoundsArray(barBounds);
            }
        });
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mBarXAxisRender.onSizeChange((int) mYAxisRender.getSelfBounds().right, mTopPadding, getMeasuredWidth(), getMeasuredHeight());


        mYAxisRender.onSizeChange(0, mTopPadding, getMeasuredWidth(), (int) mBarXAxisRender.getSelfBounds().top);

        Log.d(TAG, "onMeasure: " + mYAxisRender.getSelfBounds().right);

        mContentRender.onSizeChange((int) mYAxisRender.getSelfBounds().right, mTopPadding, getMeasuredWidth(), (int) mBarXAxisRender.getSelfBounds().top);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mYAxisRender.draw(canvas);
        mBarXAxisRender.draw(canvas);
        mContentRender.draw(canvas);
    }


    public void setDayHeartData(DayHeartData dayHeartData) {
        mDayHeartData = dayHeartData;
        mYAxisRender.setMaxYValue(mDayHeartData.maxYValue());
        mContentRender.setDayHeartData(mDayHeartData);


        invalidate();
    }
}
