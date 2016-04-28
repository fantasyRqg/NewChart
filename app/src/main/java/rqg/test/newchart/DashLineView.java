package rqg.test.newchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * *Created by rqg on 4/26/16.
 */
public class DashLineView extends View {
    Path mLinePath = new Path();
    Paint mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public DashLineView(Context context) {
        super(context);
    }

    public DashLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DashLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mLinePath, mLinePaint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initPath();
    }


    private void initPath() {
        mLinePath.reset();

        mLinePath.moveTo(0, 0);
        mLinePath.lineTo(300, 400);
        mLinePath.lineTo(400, 100);
        mLinePath.lineTo(500, 500);

        mLinePaint.setStrokeWidth(10);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(Color.RED);

        CornerPathEffect cornerPathEffect = new CornerPathEffect(1);
        DashPathEffect dpe = new DashPathEffect(new float[]{10, 10}, 5);

        ComposePathEffect cpe = new ComposePathEffect(cornerPathEffect, dpe);
        mLinePaint.setPathEffect(cpe);
    }
}
