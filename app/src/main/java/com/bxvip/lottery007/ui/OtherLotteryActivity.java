package com.bxvip.lottery007.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bxvip.lottery007.R;
import com.bxvip.lottery007.adapter.OtherLotteryAdapter;
import com.bxvip.lottery007.base.BaseAppCompatActivity;
import com.bxvip.lottery007.constant.UrlConstants;
import com.lwh.jackknife.ioc.annotation.ContentView;
import com.lwh.jackknife.ioc.annotation.OnClick;
import com.lwh.jackknife.ioc.annotation.ViewInject;

import java.util.ArrayList;

import cn.jackwhliu.rvadapter.lib.BaseRVAdapter;

@ContentView(R.layout.activity_other_lottery)
public class OtherLotteryActivity extends BaseAppCompatActivity implements UrlConstants {

    String[] mUrls = new String[] {
        URL_BJ11X5_LOTTERY_RESULT,
            URL_AHK3_LOTTERY_RESULT,
            URL_XJSSC_LOTTERY_RESULT,
            URL_BJK3_LOTTERY_RESULT
    };

    @ViewInject(R.id.rv_otherlottery_name)
    RecyclerView rv_otherlottery_name;

    OtherLotteryAdapter mAdapter;

    @OnClick(R.id.iv_otherlottery_back)
    public void onBack(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rv_otherlottery_name.setLayoutManager(new LinearLayoutManager(this));
        rv_otherlottery_name.setItemAnimator(new DefaultItemAnimator());
        rv_otherlottery_name.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        final ArrayList<String> otherLotteries = new ArrayList<>();
        otherLotteries.add("北京11选5");
        otherLotteries.add("安徽快3");
        otherLotteries.add("新疆时时彩");
        otherLotteries.add("北京快3");
        mAdapter = new OtherLotteryAdapter(this, otherLotteries);
        mAdapter.setOnItemClickListener(new BaseRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup viewGroup, int pos) {
                Intent intent = new Intent(OtherLotteryActivity.this, OtherLotteryResultActivity.class);
                intent.putExtra("pos", pos);
                intent.putExtra("title", otherLotteries.get(pos));
                intent.putExtra("lottery_url", mUrls[pos]);
                startActivity(intent);
            }
        });
        rv_otherlottery_name.setAdapter(mAdapter);
    }
}
