package com.bxvip.lottery007.adapter;

import android.content.Context;

import com.bxvip.lottery007.R;
import com.bxvip.lottery007.bean.PushMsg;
import com.lwh.jackknife.widget.ViewHolder;
import com.lwh.jackknife.util.TimeUtils;

import java.util.ArrayList;

import cn.jackwhliu.rvadapter.lib.BaseRVAdapter;

public class PushMsgAdapter extends BaseRVAdapter<PushMsg> {

    public PushMsgAdapter(Context context, ArrayList<PushMsg> datas) {
        super(context, datas);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, PushMsg msg) {
        holder.setText(R.id.tv_pushmsg_time, TimeUtils.long2str(msg.getTime(), TimeUtils.FORMAT_DATE_TIME_2));
        holder.setText(R.id.tv_pushmsg_content, msg.getContent());
    }

    @Override
    protected int[] getItemViewIds() {
        return new int[] {
                R.id.tv_pushmsg_time,
                R.id.tv_pushmsg_content
        };
    }

    @Override
    protected int getItemId() {
        return R.layout.item_push_msg;
    }
}