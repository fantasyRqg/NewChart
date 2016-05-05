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
import rqg.fantasy.newchart.chart.bhr.BhrData;
import rqg.fantasy.newchart.chart.bhr.BhrHeartChart;
import rqg.fantasy.newchart.chart.bhr.BhrHeartData;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private BhrHeartChart mBhrHeartChart;
    private AppCompatSeekBar mSeekBar;
    private BhrHeartData mBhrHeartData;

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

        mSeekBar.setProgress(7);
        setChartHeartDayData(7);

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

        Random random = new Random(System.currentTimeMillis());

        BhrHeartData bhd = new BhrHeartData();

        ArrayList<BhrData> yList = new ArrayList<>(dataSize);
        for (int i = 0; i < dataSize; i++) {
            int max, min, base;

            max = random.nextInt(300 - 40) + 40;
            int b = random.nextInt(max);
            min = random.nextInt(max - b) + b;
            base = random.nextInt(max - min) + min;
            BhrData bhrData = new BhrData();
            bhrData.max = max;
            bhrData.min = min;
            bhrData.bhr = base;

            yList.add(bhrData);
        }


        ArrayList<String> xList = new ArrayList<>(dataSize);
        xList.add("MON");
        xList.add("TUE");
        xList.add("WED");
        xList.add("THR");
        xList.add("FRI");
        xList.add("SAT");
        xList.add("SUN");

        bhd.setyValueList(yList);
        bhd.setxLableList(xList);

        mBhrHeartData = bhd;

        mBhrHeartChart.setChartData(mBhrHeartData);

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
            mTextView.setText(String.valueOf(mBhrHeartData.getY(index).bhr));
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
