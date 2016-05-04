package rqg.fantasy.newchart.chart.day;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import rqg.fantasy.newchart.chart.ChartPoint;

/**
 * *Created by rqg on 5/3/16.
 */
public class DashLineRender {
    private static final String TAG = "DashLineRender";

    private Paint mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private CornerPathEffect mCornerPathEffect;
    private ComposePathEffect mDashPathEffect;

    private float mPathWidth = 8;


    private Path mSolidPath = new Path(), mDashPath = new Path();

    public DashLineRender() {
        mLinePaint.setColor(Color.WHITE);
        mLinePaint.setStrokeWidth(mPathWidth);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeCap(Paint.Cap.ROUND);


        mCornerPathEffect = new CornerPathEffect(1f);

        DashPathEffect dpe = new DashPathEffect(new float[]{10, 15}, 0);
        mDashPathEffect = new ComposePathEffect(mCornerPathEffect, dpe);
    }


    public void draw(Canvas canvas) {
        mLinePaint.setPathEffect(mCornerPathEffect);
        canvas.drawPath(mSolidPath, mLinePaint);

        mLinePaint.setPathEffect(mDashPathEffect);
        canvas.drawPath(mDashPath, mLinePaint);


    }

    public void emptyPath() {
        mSolidPath.rewind();
        mDashPath.rewind();
    }

    public void computePath(RectF[] barBoundsArray) {
        ChartPoint[] points = new ChartPoint[barBoundsArray.length];


        for (int i = 0; i < barBoundsArray.length; i++) {
            RectF rf = barBoundsArray[i];
            if (rf.height() > 0f) {
                points[i] = new ChartPoint(rf.centerX(), rf.top);
            }
        }
        computePath(points);
    }

    public void computePath(ChartPoint[] pointArray) {
        mSolidPath.reset();
        mDashPath.reset();


        ChartPoint lastCP = null;
        ChartPoint llCp = null;
        for (int i = 0; i < pointArray.length; i++) {
            ChartPoint cp = pointArray[i];

            if (i == 0) {
                if (cp != null) {
                    mSolidPath.moveTo(cp.x, cp.y);
                    mDashPath.moveTo(cp.x, cp.y);
                }
            } else {
                if (cp == null) {
                    if (lastCP != null) {
                        mDashPath.moveTo(lastCP.x, lastCP.y);
                    }
                } else {
                    if (lastCP == null) {
                        if (mDashPath.isEmpty()) {
                            mDashPath.moveTo(cp.x, cp.y);
                        } else {
                            mDashPath.lineTo(cp.x, cp.y);
                        }
                    } else if (llCp == null) {
                        mSolidPath.moveTo(lastCP.x, lastCP.y);
                        mSolidPath.lineTo(cp.x, cp.y);
                    } else {
                        mSolidPath.lineTo(cp.x, cp.y);
                    }
                }
            }

            llCp = lastCP;
            lastCP = cp;

        }

    }
}
