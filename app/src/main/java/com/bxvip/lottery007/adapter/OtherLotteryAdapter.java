package com.bxvip.lottery007.adapter;

import android.content.Context;
import android.widget.TextView;

import com.bxvip.lottery007.R;

import java.util.ArrayList;

import cn.jackwhliu.rvadapter.lib.BaseRVAdapter;

public class OtherLotteryAdapter extends BaseRVAdapter<String> {

    public OtherLotteryAdapter(Context context, ArrayList<String> datas) {
        super(context, datas);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int pos, String s) {
        TextView tv_otherlottery_name = (TextView) holder.findViewById(R.id.tv_otherlottery_name);
        tv_otherlottery_name.setText(s);
    }

    @Override
    protected int[] getItemViewIds() {
        return new int[] {
                R.id.tv_otherlottery_name
        };
    }

    @Override
    protected int getItemId() {
        return R.layout.item_other_lottery;
    }
}
