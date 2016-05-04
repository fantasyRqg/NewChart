package rqg.fantasy.newchart.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * View that can be displayed when selecting values in the chart. Extend this class to provide custom layouts for your
 * markers.
 *
 * @author Philipp Jahoda
 */
public abstract class MarkerView extends RelativeLayout {

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public MarkerView(Context context, int layoutResource) {
        super(context);
        setupLayoutResource(layoutResource);
    }

    /**
     * Sets the layout resource for a custom MarkerView.
     *
     * @param layoutResource
     */
    private void setupLayoutResource(int layoutResource) {

        View inflated = LayoutInflater.from(getContext()).inflate(layoutResource, this);

        inflated.setLayoutParams(new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        inflated.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

        // measure(getWidth(), getHeight());
        inflated.layout(0, 0, inflated.getMeasuredWidth(), inflated.getMeasuredHeight());
    }

    /**
     * * Draws the MarkerView on the given position on the screen with the given Canvas object.
     *
     * @param canvas
     * @param posx
     * @param posy
     * @param pBounds
     */
    public void draw(Canvas canvas, float posx, float posy, RectF pBounds) {

        // take offsets into consideration
        posx += getXOffset(posx, pBounds);
        posy += getYOffset(posy, pBounds);

        // translate to the correct position and draw
        canvas.save();
        canvas.translate(posx, posy);
        draw(canvas);
        canvas.restore();
    }

    public void refreshMarkerView(int index) {

        refreshContent(index);

        // mMarkerView.measure(MeasureSpec.makeMeasureSpec(0,
        // MeasureSpec.UNSPECIFIED),
        // MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        // mMarkerView.layout(0, 0, mMarkerView.getMeasuredWidth(),
        // mMarkerView.getMeasuredHeight());
        // mMarkerView.draw(mDrawCanvas, pos[0], pos[1]);

        measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }


    /**
     * This method enables a specified custom MarkerView to update it's content everytime the MarkerView is redrawn.
     */
    public abstract void refreshContent(int index);

    /**
     * Use this to return the desired offset you wish the MarkerView to have on the x-axis. By returning -(getWidth() /
     * 2) you will center the MarkerView horizontally.
     *
     * @return
     */
    public abstract int getXOffset(float translateX, RectF pBounds);

    /**
     * Use this to return the desired position offset you wish the MarkerView to have on the y-axis. By returning
     * -getHeight() you will cause the MarkerView to be above the selected value.
     *
     * @return
     */
    public abstract int getYOffset(float translateY, RectF pBounds);
}
