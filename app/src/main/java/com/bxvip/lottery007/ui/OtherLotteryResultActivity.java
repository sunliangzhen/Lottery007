package com.bxvip.lottery007.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bxvip.lottery007.R;
import com.bxvip.lottery007.adapter.LotteryResultAdapter;
import com.bxvip.lottery007.base.BaseAppCompatActivity;
import com.bxvip.lottery007.bean.json.JSON_LotteryResult;
import com.bxvip.lottery007.bean.json.LotteryResult;
import com.bxvip.lottery007.bean.json.LotteryResultData;
import com.bxvip.lottery007.dialog.SweetAlertDialog;
import com.google.gson.Gson;
import com.lwh.jackknife.ioc.annotation.ContentView;
import com.lwh.jackknife.ioc.annotation.OnClick;
import com.lwh.jackknife.ioc.annotation.ViewInject;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

@ContentView(R.layout.activity_other_lottery_result)
public class OtherLotteryResultActivity extends BaseAppCompatActivity {

    RecyclerView rv_otherlotteryresult;
    LotteryResultAdapter mAdapter;

    @ViewInject(R.id.tv_otherlotteryresult_title)
    TextView tv_otherlotteryresult_title;
    private SweetAlertDialog dialog;

    @OnClick(R.id.iv_otherlotteryresult_back)
    public void onBack(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new SweetAlertDialog(this);
        dialog.setTitleText("请稍候...");
        dialog.show();
        rv_otherlotteryresult.setLayoutManager(new LinearLayoutManager(this));
        rv_otherlotteryresult.setItemAnimator(new DefaultItemAnimator());
        rv_otherlotteryresult.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        Intent intent = getIntent();
        String url = intent.getStringExtra("lottery_url");
        int pos = intent.getIntExtra("pos", -1);
        switch (pos) {
            case 0:
                mAdapter = new LotteryResultAdapter(OtherLotteryResultActivity.this, 5);
                break;
            case 1:
                mAdapter = new LotteryResultAdapter(OtherLotteryResultActivity.this, 3);
                break;
            case 2:
                mAdapter = new LotteryResultAdapter(OtherLotteryResultActivity.this, 5);
                break;
            case 3:
                mAdapter = new LotteryResultAdapter(OtherLotteryResultActivity.this, 3);
                break;
        }
        String title = intent.getStringExtra("title");
        tv_otherlotteryresult_title.setText(title);
        rv_otherlotteryresult.setAdapter(mAdapter);
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                JSON_LotteryResult json_lotteryResult = gson.fromJson(json, JSON_LotteryResult.class);
                int code = json_lotteryResult.getShowapi_res_code();
                if (code == 0) {
                    LotteryResultData body = json_lotteryResult.getShowapi_res_body();
                    final List<LotteryResult> results = body.getResult();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.cancel();
                            mAdapter.addItems(results);
                        }
                    });
                }
            }
        });
    }
}
