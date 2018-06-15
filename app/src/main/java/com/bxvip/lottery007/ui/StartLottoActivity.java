package com.bxvip.lottery007.ui;

import android.content.Intent;
import android.database.CursorJoiner;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.lwh.jackknife.ioc.annotation.ViewIgnore;
import com.lwh.jackknife.ioc.annotation.ViewInject;
import com.lwh.jackknife.util.Logger;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

@ContentView(R.layout.activity_startlotto)
public class StartLottoActivity extends BaseAppCompatActivity {


    OkHttpClient mClient = new OkHttpClient();;

    @ViewInject(R.id.rv_startlotto)
    RecyclerView rv_start_lotto;

    @ViewInject(R.id.tv_startlotto_title)
    TextView tv_startlotto_title;

    LotteryResultAdapter mAdapter;

    int mChannel = -1;

    private SweetAlertDialog dialog;

    @OnClick(R.id.iv_startlotto_back)
    public void onBack(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new SweetAlertDialog(this);
        dialog.setTitleText("请稍候...");
        dialog.show();
        rv_start_lotto.setLayoutManager(new LinearLayoutManager(this));
        rv_start_lotto.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv_start_lotto.setItemAnimator(new DefaultItemAnimator());
        Intent intent = getIntent();
        mChannel = intent.getIntExtra("channel", -1);
        String[] urls = new String[] {
                "http://route.showapi.com/44-2?showapi_sign=4d2024758fde4f6985bf8eeb6095dc89&showapi_appid=45601&code=ssq",
                "http://route.showapi.com/44-2?showapi_sign=4d2024758fde4f6985bf8eeb6095dc89&showapi_appid=45601&code=fc3d",
                "http://route.showapi.com/44-2?showapi_sign=4d2024758fde4f6985bf8eeb6095dc89&showapi_appid=45601&code=qlc",
                "http://route.showapi.com/44-2?showapi_sign=4d2024758fde4f6985bf8eeb6095dc89&showapi_appid=45601&code=dlt",
                "http://route.showapi.com/44-2?showapi_sign=4d2024758fde4f6985bf8eeb6095dc89&showapi_appid=45601&code=pl3",
                "http://route.showapi.com/44-2?showapi_sign=4d2024758fde4f6985bf8eeb6095dc89&showapi_appid=45601&code=pl5",
                "http://route.showapi.com/44-2?showapi_sign=4d2024758fde4f6985bf8eeb6095dc89&showapi_appid=45601&code=qxc"
        };
        switch (mChannel) {
            case 0:
                tv_startlotto_title.setText("双色球");
                mAdapter = new LotteryResultAdapter(this, 6);
                break;
            case 1:
                tv_startlotto_title.setText("3D");
                mAdapter = new LotteryResultAdapter(this, 3);
                break;
            case 2:
                tv_startlotto_title.setText("7乐彩");
                mAdapter = new LotteryResultAdapter(this, 7);
                break;
            case 3:
                tv_startlotto_title.setText("大乐透");
                mAdapter = new LotteryResultAdapter(this, 5);
                break;
            case 4:
                tv_startlotto_title.setText("排列三");
                mAdapter = new LotteryResultAdapter(this, 3);
                break;
            case 5:
                tv_startlotto_title.setText("排列五");
                mAdapter = new LotteryResultAdapter(this, 5);
                break;
            case 6:
                tv_startlotto_title.setText("7星彩");
                mAdapter = new LotteryResultAdapter(this, 7);
                break;
        }
        mAdapter.setOnItemClickListener(new LotteryResultAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, int position) {
                Intent intent = new Intent(StartLottoActivity.this, LotteryDetailActivity.class);
                intent.putExtra("pos", mChannel);
                intent.putExtra("lottery", mAdapter.getData(position));
                startActivity(intent);
            }
        });
        rv_start_lotto.setAdapter(mAdapter);
        final Request request = new Request.Builder()
                .url(urls[mChannel])
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                JSON_LotteryResult json_result = gson.fromJson(json, JSON_LotteryResult.class);
                int showapi_res_code = json_result.getShowapi_res_code();
                if (showapi_res_code == 0) {
                    LotteryResultData body = json_result.getShowapi_res_body();
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
