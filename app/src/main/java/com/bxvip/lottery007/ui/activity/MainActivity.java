package com.bxvip.lottery007.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.bxvip.lottery007.R;
import com.bxvip.lottery007.base.BaseAppCompatActivity;
import com.lwh.jackknife.ioc.annotation.ContentView;
import com.lwh.jackknife.ioc.annotation.OnClick;
import com.lwh.jackknife.ioc.annotation.ViewIgnore;
import com.lwh.jackknife.ioc.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseAppCompatActivity {

    @ViewInject(R.id.rl_bottom_1)
    RelativeLayout rl_bottom_1;
    @ViewInject(R.id.rl_bottom_2)
    RelativeLayout rl_bottom_2;
    @ViewInject(R.id.rl_bottom_3)
    RelativeLayout rl_bottom_3;

    @OnClick(R.id.rl_bottom_1)
    public void startCalendarActivity(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rl_bottom_2)
    public void startWeatherActivity(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rl_bottom_3)
    public void startProfileActivity(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
