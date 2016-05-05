package rqg.fantasy.chart.bhr;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import java.util.ArrayList;

import rqg.fantasy.chart.ChartPoint;
import rqg.fantasy.chart.render.BaseBarDashLineContentRender;

/**
 * *Created by rqg on 5/4/16.
 */
public class BhrContentRender extends BaseBarDashLineContentRender<BhrHeartData> {

    protected Paint mContentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    protected LinearGradient mLinearGradient;
    protected Paint mHolePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int HOLE_WIDTH = 8;
    private static final int HOLE_RADIUS = 8;

    private int mWhite = Color.parseColor("#F2FFFF");
    private int mTransparent = Color.parseColor("#00FFFFFF");
    private ChartPoint[] mBhrPointArray;

    public BhrContentRender(int maxWidth) {
        super(maxWidth);

        mDashLineRender.setPathWidth(5);
        mHolePaint.setColor(Color.parseColor("#EF711B"));
        mHolePaint.setStrokeWidth(HOLE_WIDTH);
        mHolePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void initBarBoundsAndDashLine() {
        int height = (int) mSelfBounds.height();

        if (height == 0)
            return;


        ArrayList<BhrData> yList = mChartData.getyValueList();
        int size = yList.size();

        float cellWidth = mSelfBounds.width() / size;
        float width = getBarWidth(cellWidth);

        float tmpLeft = mSelfBounds.left + (cellWidth - width) / 2f;


        float maxValue = mChartData.maxYValue();

        mBarBoundsArray = new RectF[size];
        mBhrPointArray = new ChartPoint[size];

        RectF tmp = new RectF(tmpLeft, mSelfBounds.top, tmpLeft + width, mSelfBounds.bottom);

        for (int i = 0; i < mBarBoundsArray.length; i++) {
            BhrData v = yList.get(i);
            RectF bounds = null;

            if (v.max != 0 && v.min != 0) {
                bounds = new RectF(tmp);
                bounds.top = tmp.bottom - v.max / maxValue * height;
                bounds.bottom = tmp.bottom - v.min / maxValue * height;
            }

            ChartPoint cp = null;
            if (v.bhr != 0) {
                cp = new ChartPoint(tmp.centerX(), tmp.bottom - v.bhr / maxValue * height);
            }

            tmp.offset(cellWidth, 0);
            mBarBoundsArray[i] = bounds;
            mBhrPointArray[i] = cp;

        }

        mDashLineRender.computePath(mBhrPointArray);
    }

    @Override
    public void onSizeChange(int left, int top, int right, int bottom) {
        super.onSizeChange(left, top, right, bottom);
        mLinearGradient = new LinearGradient(mSelfBounds.left, mSelfBounds.top, mSelfBounds.left, mSelfBounds.bottom,
                new int[]{mWhite, mTransparent}, null, Shader.TileMode.REPEAT);

        mContentPaint.setShader(mLinearGradient);
    }

    @Override
    public void draw(Canvas canvas) {

        if (mBarBoundsArray != null) {

            for (RectF b : mBarBoundsArray) {
                if (b == null)
                    continue;

                float r = Math.min(b.height(), b.width()) / 2f;
                canvas.drawRoundRect(b, r, r, mContentPaint);
            }
        }
        mDashLineRender.draw(canvas);

        if (mBhrPointArray != null) {
            for (ChartPoint cp : mBhrPointArray) {
                if (cp != null)
                    canvas.drawCircle(cp.x, cp.y, HOLE_RADIUS, mHolePaint);
            }
        }
    }
}
