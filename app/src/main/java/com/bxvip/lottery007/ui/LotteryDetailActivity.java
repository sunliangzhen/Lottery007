package com.bxvip.lottery007.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bxvip.lottery007.R;
import com.bxvip.lottery007.base.BaseAppCompatActivity;
import com.bxvip.lottery007.bean.json.LotteryResult;
import com.bxvip.lottery007.widget.LotteryBall;
import com.bxvip.lottery007.widget.LotteryView;
import com.lwh.jackknife.ioc.annotation.ContentView;

import java.util.ArrayList;

@ContentView(R.layout.activity_lottery_detail)
public class LotteryDetailActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private TextView tv_lottery_id;
    private TextView tv_lottery_dateline;
    private LotteryView lv_lottery;
    private RelativeLayout rl_trace1;
    private RelativeLayout rl_trace2;
    private RelativeLayout rl_trace3;
    private RelativeLayout rl_trace4;
    private RelativeLayout rl_trace5;
    private LotteryResult lottery;
    private int type;
    private ImageView iv_lotterydetail_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        lottery = (LotteryResult) intent.getSerializableExtra("lottery");
        type = intent.getIntExtra("pos", -1);
        setContentView(R.layout.activity_lottery_detail);
        tv_lottery_id = (TextView) findViewById(R.id.tv_lottery_id);
        tv_lottery_dateline = (TextView) findViewById(R.id.tv_lottery_dateline);
        iv_lotterydetail_back = (ImageView) findViewById(R.id.iv_lotterydetail_back);
        iv_lotterydetail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lv_lottery = (LotteryView) findViewById(R.id.lv_lottery);
        rl_trace1 = (RelativeLayout) findViewById(R.id.rl_trace1);
        rl_trace2 = (RelativeLayout) findViewById(R.id.rl_trace2);
        rl_trace3 = (RelativeLayout) findViewById(R.id.rl_trace3);
        rl_trace4 = (RelativeLayout) findViewById(R.id.rl_trace4);
        rl_trace5 = (RelativeLayout) findViewById(R.id.rl_trace5);
        rl_trace1.setOnClickListener(this);
        rl_trace2.setOnClickListener(this);
        rl_trace3.setOnClickListener(this);
        rl_trace4.setOnClickListener(this);
        rl_trace5.setOnClickListener(this);
        String number = lottery.getOpenCode().replace("+", ",");
        String[] results = number.split(",");
        ArrayList<LotteryBall> balls = new ArrayList<>();
        if (type == 0) {
            for (int i=0;i<results.length;i++) {
                if (i <6) {
                    balls.add(new LotteryBall(Color.RED, results[i]));
                } else {
                    balls.add(new LotteryBall(Color.BLUE, results[i]));
                }
            }
        } else if (type == 1) {
            for (int i=0;i<results.length;i++) {
                balls.add(new LotteryBall(Color.RED, results[i]));
            }
        } else if (type == 2) {
            for (int i=0;i<results.length;i++) {
                if (i<7) {
                    balls.add(new LotteryBall(Color.RED, results[i]));
                } else {
                    balls.add(new LotteryBall(Color.BLUE, results[i]));
                }
            }
        } else if (type == 3) {
            for (int i=0;i<results.length;i++) {
                if (i < 5) {
                    balls.add(new LotteryBall(Color.RED, results[i]));
                } else {
                    balls.add(new LotteryBall(Color.BLUE, results[i]));
                }
            }
        } else if (type == 4) {
            for (int i=0;i<results.length;i++){
                balls.add(new LotteryBall(Color.RED, results[i]));
            }
        } else if (type == 5) {
            for (int i=0;i<results.length;i++){
                balls.add(new LotteryBall(Color.RED, results[i]));
            }
        } else if (type == 6) {
            for (int i=0;i<results.length;i++){
                balls.add(new LotteryBall(Color.RED, results[i]));
            }
        }
        lv_lottery.setBalls(balls);
        lv_lottery.setCurrent(true);
        tv_lottery_dateline.setText(lottery.getTime());
        tv_lottery_id.setText("第"+lottery.getExpect()+"期");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (type) {
            case 0:
                intent.setClass(this, ShuangSeQiuChartActivity.class);
                break;
            case 1:
                intent.setClass(this, _3DChartActivity.class);
                break;
            case 2:
                intent.setClass(this, _7LeCaiChartActivity.class);
                break;
            case 3:
                intent.setClass(this, DaLeTouChartActivity.class);
                break;
            case 4:
                intent.setClass(this, PaiLie3ChartActivity.class);
                break;
            case 5:
                intent.setClass(this, PaiLie5ChartActivity.class);
                break;
            case 6:
                intent.setClass(this, QiXingCaiChartActivity.class);
                break;
        }
        switch (view.getId()) {
            case R.id.rl_trace1:
                intent.putExtra("type", 1);
                break;
            case R.id.rl_trace2:
                intent.putExtra("type", 2);
                break;
            case R.id.rl_trace3:
                intent.putExtra("type", 3);
                break;
            case R.id.rl_trace4:
                intent.putExtra("type", 4);
                break;
            case R.id.rl_trace5:
                intent.putExtra("type", 5);
                break;
        }
        intent.putExtra("lottery", lottery);
        startActivity(intent);
    }
}
