package rqg.fantasy.newchart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Random;

import rqg.fantasy.newchart.chart.day.DayHeartChart;
import rqg.fantasy.newchart.chart.day.DayHeartData;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private DayHeartChart mDayHeartChart;
    private Random mRandom = new Random(System.currentTimeMillis());
    private AppCompatSeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDayHeartChart = (DayHeartChart) findViewById(R.id.heart_chart);

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


    }


    private void setChartHeartDayData(int dataSize) {
        Log.d(TAG, "setChartHeartDayData: " + dataSize);
        DayHeartData dayHeartData = new DayHeartData();

        ArrayList<Integer> yList = new ArrayList<>(dataSize);
        for (int i = 0; i < dataSize; i++) {
            yList.add(mRandom.nextBoolean() ? 0 : mRandom.nextInt(300));
        }

//        yList.add(120);
//        yList.add(160);
//        yList.add(0);
//        yList.add(110);
//        yList.add(0);
//        yList.add(10);
//        yList.add(0);
//        yList.add(100);
//        yList.add(0);
//        yList.add(10);

        dayHeartData.setyValueList(yList);
        mDayHeartChart.setDayHeartData(dayHeartData);
    }

    public void refresh(View view) {
        setChartHeartDayData(mSeekBar.getProgress());
    }
}
