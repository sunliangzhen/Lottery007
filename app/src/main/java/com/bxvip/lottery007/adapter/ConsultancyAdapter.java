package com.bxvip.lottery007.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bxvip.lottery007.R;
import com.bxvip.lottery007.bean.json.DataList;

import cn.jackwhliu.rvadapter.lib.BaseRVAdapter;

public class ConsultancyAdapter extends BaseRVAdapter<DataList> {

    public ConsultancyAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, final DataList dataList) {
        TextView tv_title = (TextView) holder.findViewById(R.id.tv_title);
        TextView tv_summary = (TextView) holder.findViewById(R.id.tv_summary);
        TextView tv_publishdate = (TextView) holder.findViewById(R.id.tv_publishdate);
        ImageView iv_logo_file = (ImageView) holder.findViewById(R.id.iv_logo_file);
        iv_logo_file.setImageResource(R.drawable.consultancy);
        tv_title.setText(dataList.getTitle());
        tv_summary.setText(dataList.getSummary());
        tv_publishdate.setText(dataList.getPublishDate());
        String logoFile = dataList.getLogoFile();
        if (logoFile != null) {
            Glide.with(getContext()).load(logoFile).into(iv_logo_file);
        }
    }

    @Override
    protected int[] getItemViewIds() {
        return new int[] {
                R.id.tv_title,
                R.id.tv_summary,
                R.id.tv_publishdate,
                R.id.iv_logo_file
        };
    }

    @Override
    protected int getItemId() {
        return R.layout.item_consultancy;
    }
}
