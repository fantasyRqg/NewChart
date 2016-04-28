package rqg.test.newchart.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * *Created by rqg on 4/27/16.
 */
abstract public class Chart extends View {

    protected ChartRender mYAxisRender, mXAxisRender, mContentRender, mIndicatorRender;

    public Chart(Context context) {
        this(context, null);
    }

    public Chart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Chart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mYAxisRender.onSizeChange(getMeasuredWidth(), getMeasuredHeight());
        mXAxisRender.onSizeChange(getMeasuredWidth(), getMeasuredHeight());
        mContentRender.onSizeChange(getMeasuredWidth(), getMeasuredHeight());
        mIndicatorRender.onSizeChange(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mYAxisRender.draw(canvas);
        mXAxisRender.draw(canvas);
        mContentRender.draw(canvas);
        mIndicatorRender.draw(canvas);
    }
}
