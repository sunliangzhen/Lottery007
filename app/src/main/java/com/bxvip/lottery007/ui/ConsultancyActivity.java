package com.bxvip.lottery007.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bxvip.lottery007.R;
import com.bxvip.lottery007.adapter.ConsultancyAdapter;
import com.bxvip.lottery007.base.BaseAppCompatActivity;
import com.bxvip.lottery007.bean.json.DataList;
import com.bxvip.lottery007.bean.json.JSONConsultancy;
import com.bxvip.lottery007.dialog.SweetAlertDialog;
import com.bxvip.lottery007.widget.PullableRecyclerView;
import com.bxvip.lottery007.widget.SwipeLayout;
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
import java.util.ArrayList;
import java.util.List;

import cn.jackwhliu.rvadapter.lib.BaseRVAdapter;

@ContentView(R.layout.activity_consultancy)
public class ConsultancyActivity extends BaseAppCompatActivity {

    private ConsultancyAdapter mAdapter;
    @ViewInject(R.id.rv_consultancy)
    private PullableRecyclerView rv_consultancy;
    @ViewInject(R.id.rl_consultancy_cp)
    private RelativeLayout rl_consultancy_cp;
    @ViewInject(R.id.swipelayout)
    SwipeLayout swipelayout;
    @ViewInject(R.id.rl_consultancy)
    RelativeLayout rl_consultancy;
    List<DataList> mConsultancies = new ArrayList<>();
    int pageNum = 0;
    SweetAlertDialog dialog;
    @Override
    public void onStart() {
        super.onStart();
        pageNum = 0;
    }

    @OnClick(R.id.iv_consultancy_back)
    public void onBack(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new SweetAlertDialog(this);
        dialog.setTitleText("请稍候...");
        dialog.show();
        rv_consultancy.setLayoutManager(new LinearLayoutManager(this));
        rv_consultancy.setItemAnimator(new DefaultItemAnimator());
        rv_consultancy.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new ConsultancyAdapter(this);
        mAdapter.setOnItemClickListener(new BaseRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, int pos) {
                Intent intent = new Intent(ConsultancyActivity.this, ConsultancyDetailActivity.class);
                intent.putExtra("url", mAdapter.getData(pos).getUrl());
                startActivity(intent);
            }
        });
        swipelayout.setOnRefreshListener(new SwipeLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipeLayout swipeLayout) {
                mAdapter.clear();
                pageNum = 0;
                fetchDatas();
            }

            @Override
            public void onLoadMore(final SwipeLayout swipeLayout) {
                ArrayList<DataList> items = getMore();
                if (items != null && items.size() > 0) {
                    mAdapter.addItems(items);
                    swipeLayout.loadmoreFinish(SwipeLayout.SUCCEED);
                } else {
                    swipeLayout.loadmoreFinish(SwipeLayout.FAIL);
                }
            }
        });
        rv_consultancy.setAdapter(mAdapter);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=100&busiCode=300203&src=0000100001%7C6000003060")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.cancel();
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                final JSONConsultancy JSONConsultancy = gson.fromJson(json, JSONConsultancy.class);
                swipelayout.setVisibility(View.VISIBLE);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mConsultancies = JSONConsultancy.getDataList();
                        ArrayList<DataList> items = getMore();
                        if (items != null && items.size() > 0) {
                            mAdapter.addItems(items);
                            rl_consultancy.setVisibility(View.GONE);
                            dialog.cancel();
                        }
                    }
                });
            }
        });
    }

    private void fetchDatas() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=100&busiCode=300203&src=0000100001%7C6000003060")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        swipelayout.refreshFinish(SwipeLayout.FAIL);
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                final JSONConsultancy JSONConsultancy = gson.fromJson(json, JSONConsultancy.class);
                rl_consultancy_cp.setVisibility(View.GONE);
                swipelayout.setVisibility(View.VISIBLE);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mConsultancies = JSONConsultancy.getDataList();
                        ArrayList<DataList> items = getMore();
                        if (items != null && items.size() > 0) {
                            mAdapter.addItems(items);
                            swipelayout.refreshFinish(SwipeLayout.SUCCEED);
                        }
                    }
                });
            }
        });
    }

    private ArrayList<DataList> getMore() {
        ArrayList<DataList> consultancies = new ArrayList<>();
        if (pageNum < 10 && mConsultancies != null && mConsultancies.size() > 0) {
            int start = pageNum * 10;
            for (int i = start; i < start + 10; i++) {
                consultancies.add(mConsultancies.get(i));
            }
            if (pageNum <= 10) {
                pageNum++;
            }
        }
        return consultancies;
    }
}
