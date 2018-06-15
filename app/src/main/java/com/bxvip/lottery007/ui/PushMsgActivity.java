package com.bxvip.lottery007.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bxvip.lottery007.R;
import com.bxvip.lottery007.adapter.PushMsgAdapter;
import com.bxvip.lottery007.base.BaseAppCompatActivity;
import com.bxvip.lottery007.bean.PushMsg;
import com.lwh.jackknife.orm.builder.QueryBuilder;
import com.lwh.jackknife.orm.dao.DaoFactory;
import com.lwh.jackknife.orm.dao.OrmDao;

import java.util.ArrayList;

public class PushMsgActivity extends BaseAppCompatActivity {

    RecyclerView rv_pushmsg;
    PushMsgAdapter mAdapter;
    ImageView iv_pushmsg_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_msg);
        rv_pushmsg = (RecyclerView) findViewById(R.id.rv_pushmsg);
        iv_pushmsg_back = (ImageView) findViewById(R.id.iv_pushmsg_back);
        iv_pushmsg_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rv_pushmsg.setLayoutManager(new LinearLayoutManager(this));
        rv_pushmsg.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv_pushmsg.setItemAnimator(new DefaultItemAnimator());
        OrmDao<PushMsg> dao = DaoFactory.getDao(PushMsg.class);
        ArrayList<PushMsg> msgs = (ArrayList<PushMsg>) dao.select(QueryBuilder.create().orderBy("save_time DESC").limit(30));
        mAdapter = new PushMsgAdapter(this, msgs);
        rv_pushmsg.setAdapter(mAdapter);
    }
}
