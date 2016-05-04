package rqg.fantasy.newchart;

import android.content.Context;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import rqg.fantasy.newchart.chart.MarkerView;
import rqg.fantasy.newchart.chart.bhr.BhrHeartChart;
import rqg.fantasy.newchart.chart.day.DayHeartData;

public class MainActivity extends AppCompatActivity {


    private BhrHeartChart mBhrHeartChart;
    private Random mRandom = new Random(System.currentTimeMillis());
    private AppCompatSeekBar mSeekBar;
    private DayHeartData mDayHeartData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BHR Heart Chart");

        mBhrHeartChart = (BhrHeartChart) findViewById(R.id.heart_chart);

        mSeekBar = (AppCompatSeekBar) findViewById(R.id.seek_bar);


        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setChartHeartDayData(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekBar.setMax(500);

        mSeekBar.setProgress(24);
        setChartHeartDayData(24);

        mBhrHeartChart.setMarkerView(new DayHeartMarkerView(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 0, 0, "BHR");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        return true;
    }


    private void setChartHeartDayData(int dataSize) {
        DayHeartData dayHeartData = new DayHeartData();

        ArrayList<Integer> yList = new ArrayList<>(dataSize);
        for (int i = 0; i < dataSize; i++) {
            yList.add(mRandom.nextBoolean() ? 0 : mRandom.nextInt(300));
        }

        dayHeartData.setyValueList(yList);
//        mBhrHeartChart.setBhrHeartData(dayHeartData);

        mDayHeartData = dayHeartData;
    }

    public void refresh(View view) {
        setChartHeartDayData(mSeekBar.getProgress());
    }


    public class DayHeartMarkerView extends MarkerView {

        TextView mTextView;

        public DayHeartMarkerView(Context context) {
            super(context, R.layout.mark_heart);
            mTextView = (TextView) findViewById(R.id.heart_value);
        }


        @Override
        public void refreshContent(int index) {
            mTextView.setText(String.valueOf(mDayHeartData.getY(index)));
        }

        @Override
        public int getXOffset(float translateX, RectF pBounds) {
            return 0;
        }

        @Override
        public int getYOffset(float translateY, RectF pBounds) {

            return -getHeight() / 2;
        }
    }
}
