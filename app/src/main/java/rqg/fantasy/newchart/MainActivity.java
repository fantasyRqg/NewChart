package rqg.fantasy.newchart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

import rqg.fantasy.newchart.chart.day.DayHeartChart;
import rqg.fantasy.newchart.chart.day.DayHeartData;

public class MainActivity extends AppCompatActivity {

    private DayHeartChart mDayHeartChart;
    private Random mRandom = new Random(System.currentTimeMillis());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDayHeartChart = (DayHeartChart) findViewById(R.id.heart_chart);

        setChartHeartDayData();
    }


    private void setChartHeartDayData() {
        DayHeartData dayHeartData = new DayHeartData();
        int dataSize = 10;
        ArrayList<Integer> yList = new ArrayList<>(dataSize);
        for (int i = 0; i < dataSize; i++) {
            yList.add(mRandom.nextInt(300));
        }

        dayHeartData.setyValueList(yList);
        mDayHeartChart.setDayHeartData(dayHeartData);
    }
}
