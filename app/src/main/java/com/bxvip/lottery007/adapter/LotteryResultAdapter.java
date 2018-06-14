package com.bxvip.lottery007.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.bxvip.lottery007.R;
import com.bxvip.lottery007.bean.json.LotteryResult;
import com.bxvip.lottery007.widget.LotteryBall;
import com.bxvip.lottery007.widget.LotteryView;

import java.util.ArrayList;

import cn.jackwhliu.rvadapter.lib.BaseRVAdapter;

public class LotteryResultAdapter extends BaseRVAdapter<LotteryResult> {

    int mRedCount;

    public LotteryResultAdapter(Context context) {
        super(context);
    }

    public LotteryResultAdapter(Context context, int redCount) {
        super(context);
        this.mRedCount = redCount;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, LotteryResult data) {
        TextView tv_today_pid = (TextView) holder.findViewById(R.id.tv_today_pid);
        TextView tv_today_time = (TextView) holder.findViewById(R.id.tv_today_time);
        LotteryView lv_today_number = (LotteryView) holder.findViewById(R.id.lv_today_number);
        tv_today_pid.setText(data.getExpect());
        tv_today_time.setText(data.getTime());
        String openCode = data.getOpenCode();
        String[] numbers = openCode.replace("+", ",").split(",");
        ArrayList<LotteryBall> balls = new ArrayList<>();
        for (int i=0;i<numbers.length;i++) {
            LotteryBall ball;
            if (i < mRedCount) {
                ball = new LotteryBall(Color.RED, numbers[i]);
            } else {
                ball = new LotteryBall(Color.BLUE, numbers[i]);
            }
            balls.add(ball);
        }
        lv_today_number.setBalls(balls);
    }

    @Override
    protected int[] getItemViewIds() {
        return new int[] {
                R.id.tv_today_pid,
                R.id.tv_today_time,
                R.id.lv_today_number
        };
    }

    @Override
    protected int getItemId() {
        return R.layout.item_today_result;
    }
}
