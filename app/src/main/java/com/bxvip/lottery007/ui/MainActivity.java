package com.bxvip.lottery007.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bxvip.lottery007.R;
import com.bxvip.lottery007.base.BaseAppCompatActivity;
import com.bxvip.lottery007.widget.CircleImageView;
import com.bxvip.lottery007.widget.CircleLayout;
import com.lwh.jackknife.ioc.annotation.ContentView;
import com.lwh.jackknife.ioc.annotation.OnClick;
import com.lwh.jackknife.util.ToastUtils;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseAppCompatActivity implements CircleLayout.OnItemSelectedListener, CircleLayout.OnCenterClickListener {

    TextView tv_main_selected;
    CircleLayout cl_main;
    ImageView iv_main_calendar;
    ImageView iv_main_cloud;
    ImageView iv_main_profile;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Drawable drawable1 = iv_main_calendar.getDrawable();
        drawable1.setBounds(0, 0, 40, 40);
        iv_main_calendar.setImageDrawable(drawable1);
        Drawable drawable2 = iv_main_cloud.getDrawable();
        drawable2.setBounds(0, 0, 40, 40);
        iv_main_cloud.setImageDrawable(drawable2);
        Drawable drawable3 = iv_main_profile.getDrawable();
        drawable3.setBounds(0, 0, 40, 40);
        iv_main_profile.setImageDrawable(drawable3);
        String name = null;
        View view = cl_main.getSelectedItem();
        if (view instanceof CircleImageView) {
            name = ((CircleImageView) view).getName();
        }
        tv_main_selected.setText(name);
        cl_main.setOnItemSelectedListener(this);
        cl_main.setOnCenterClickListener(this);
        cl_main.setOnItemClickListener(new CircleLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                final String name;
                if (view instanceof CircleImageView) {
                    name = ((CircleImageView) view).getName();
                } else {
                    name = null;
                }
                tv_main_selected.setText(name);
                switch (view.getId()) {
                    case R.id.main_calendar_image:
                        startLotto(0);
                        break;
                    case R.id.main_cloud_image:
                        startLotto(1);
                        break;
                    case R.id.main_mail_image:
                        startLotto(2);
                        break;
                    case R.id.main_key_image:
                        startLotto(3);
                        break;
                    case R.id.main_profile_image:
                        startLotto(4);
                        break;
                    case R.id.main_tap_image:
                        startLotto(5);
                        break;
                    case R.id.main_aa_image:
                        startLotto(6);
                        break;
                }
            }
        });
    }

    private void startLotto(final int pos) {
        Intent intent = new Intent(this, StartLottoActivity.class);
        intent.putExtra("channel", pos);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(View view) {
        final String name;
        if (view instanceof CircleImageView) {
            name = ((CircleImageView) view).getName();
        } else {
            name = null;
        }
        tv_main_selected.setText(name);
    }

    @Override
    public void onCenterClick() {
        ToastUtils.showShort(this, "hf");
    }
}
