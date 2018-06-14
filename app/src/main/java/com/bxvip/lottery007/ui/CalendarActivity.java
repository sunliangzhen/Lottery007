package com.bxvip.lottery007.ui;

import android.view.View;

import com.bxvip.lottery007.R;
import com.bxvip.lottery007.base.BaseAppCompatActivity;
import com.lwh.jackknife.ioc.annotation.ContentView;
import com.lwh.jackknife.ioc.annotation.OnClick;

/**
 * 日历界面
 */
@ContentView(R.layout.activity_calendar)
public class CalendarActivity extends BaseAppCompatActivity {

    @OnClick(R.id.iv_calendar_back)
    public void onBack(View view) {
        finish();
    }
}
