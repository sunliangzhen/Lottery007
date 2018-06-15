package com.bxvip.lottery007.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bxvip.lottery007.R;
import com.bxvip.lottery007.base.BaseAppCompatActivity;
import com.bxvip.lottery007.bean.json.LotteryResult;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.lwh.jackknife.ioc.annotation.ContentView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ContentView(R.layout.activity_qixingcai_chart)
public class QiXingCaiChartActivity extends BaseAppCompatActivity {

    BarChart barchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int type = intent.getIntExtra("type", 0);
        LotteryResult lottery = (LotteryResult) intent.getSerializableExtra("lottery");
        setContentView(R.layout.activity_qixingcai_chart);
        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        barchart = (BarChart) findViewById(R.id.barchart);
        barchart.getDescription().setEnabled(false);
        barchart.setTouchEnabled(false);
        barchart.setNoDataText("没有数据");
        barchart.animateY(2000);
        barchart.getAxisLeft().setAxisMinimum(0);
        barchart.getAxisRight().setAxisMinimum(0);
        barchart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);
//        barchart.getAxisRight().setEnabled(false);
        XAxis xAxis = barchart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(7);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(900);
        xAxis.setEnabled(false);
        BarData data = new BarData();
        data.setBarWidth(50);
        List<BarEntry> barEntries1 = new ArrayList<>();
        String number = lottery.getOpenCode().replace("+", ",");
        String[] nums = number.split(",");
        int n1 = Integer.valueOf(nums[0]);
        int n2 = Integer.valueOf(nums[1]);
        int n3 = Integer.valueOf(nums[2]);
        int n4 = Integer.valueOf(nums[3]);
        int n5 = Integer.valueOf(nums[4]);
        int n6 = Integer.valueOf(nums[5]);
        int n7 = Integer.valueOf(nums[6]);
        barEntries1.add(new BarEntry(100, n1));
        barEntries1.add(new BarEntry(200, n2));
        barEntries1.add(new BarEntry(300, n3));
        barEntries1.add(new BarEntry(400, n4));
        barEntries1.add(new BarEntry(500, n5));
        barEntries1.add(new BarEntry(600, n6));
        barEntries1.add(new BarEntry(700, n7));
        List<BarEntry> barEntries3 = new ArrayList<>();
        ArrayList<Integer> sortList = new ArrayList<>();
        sortList.add(n1);
        sortList.add(n2);
        sortList.add(n3);
        sortList.add(n4);
        sortList.add(n5);
        sortList.add(n6);
        sortList.add(n7);
        Collections.sort(sortList);
        if (type == 1) {
            barEntries3.add(new BarEntry(800, n1+n2+n3+n4+n5+n6+n7));
        } else if (type == 2) {
            barEntries3.add(new BarEntry(800, (n1+n2+n3+n4+n5+n6+n7)/ 7));
        } else if (type == 3) {
            barEntries3.add(new BarEntry(800, sortList.get(3)));
        } else if (type == 4) {
            barEntries3.add(new BarEntry(800, sortList.get(6) - sortList.get(0)));
        } else if (type == 5) {
            int avg = (n1+n2+n3+n4+n5+n6+n7)/ 7;
            double result = Math.sqrt(((double)(n1-avg)*(n1-avg)+(n2-avg)*(n2-avg)+(n3-avg)*(n3-avg)+(n4-avg)*(n4-avg)
                    +(n5-avg)*(n5-avg)+(n6-avg)*(n6-avg)+(n7-avg)*(n7-avg))/ 7);
            barEntries3.add(new BarEntry(800, (float) result));
        }
        BarDataSet dataSet1 = new BarDataSet(barEntries1, "红球");
        dataSet1.setColor(getResources().getColor(R.color.orange_red));
        BarDataSet dataSet3 = null;
        if (type == 1) {
            dataSet3 = new BarDataSet(barEntries3, "和值");
        } else if (type == 2) {
            dataSet3 = new BarDataSet(barEntries3, "均值");
        } else if (type == 3) {
            dataSet3 = new BarDataSet(barEntries3, "中位数");
        } else if (type == 4) {
            dataSet3 = new BarDataSet(barEntries3, "极差");
        } else if (type == 5) {
            dataSet3 = new BarDataSet(barEntries3, "方差");
        }
        dataSet3.setColor(getResources().getColor(R.color.light_gray));
        data.addDataSet(dataSet1);
        data.addDataSet(dataSet3);
        data.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return ((int)value)+"";
            }
        });
        barchart.setData(data);
        barchart.invalidate();
    }
}
