package rqg.fantasy.newchart.chart.render;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import rqg.fantasy.newchart.chart.ChartRender;
import rqg.fantasy.newchart.chart.MarkerView;

/**
 * *Created by rqg on 5/4/16.
 */
public class IndicatorRender implements ChartRender {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private RectF mSelectedBounds;
    private RectF mSelfBounds = new RectF();
    private int mIndicatorColor = Color.RED;
    private LinearGradient mLinearGradient;

    private float mTopLine = 0;

    private int mWhite = Color.parseColor("#FFFFFFFF");
    private int mTransparent = Color.parseColor("#00FFFFFF");
    private MarkerView mMarkerView;


    public IndicatorRender() {
        mPaint.setColor(mIndicatorColor);
        mPaint.setStrokeWidth(2);
    }

    @Override
    public void draw(Canvas canvas) {
        if (mSelectedBounds != null) {
            if (mLinearGradient != null) {
                mPaint.setShader(mLinearGradient);
            }

            float x = mSelectedBounds.centerX();
            canvas.drawLine(x, mTopLine, x, mSelectedBounds.bottom, mPaint);

            if (mMarkerView != null) {
                mMarkerView.draw(canvas, x, mTopLine, mSelfBounds);
            }
        }
    }

    @Override
    public void onSizeChange(int left, int top, int right, int bottom) {
        mSelfBounds.set(left, top, right, bottom);
        mLinearGradient = new LinearGradient(left, top, left, bottom, new int[]{mWhite, mTransparent}, null, Shader.TileMode.REPEAT);
    }

    @Override
    public RectF getSelfBounds() {
        return mSelfBounds;
    }


    public void setSelectedBounds(RectF selectedBounds, int index) {
        mSelectedBounds = selectedBounds;
        if (mMarkerView != null) {
            mMarkerView.refreshMarkerView(index);
        }
    }


    public float getTopLine() {
        return mTopLine;
    }

    public void setTopLine(float topLine) {
        mTopLine = topLine;
    }


    public void setMarkerView(MarkerView markerView) {
        mMarkerView = markerView;
    }
}
