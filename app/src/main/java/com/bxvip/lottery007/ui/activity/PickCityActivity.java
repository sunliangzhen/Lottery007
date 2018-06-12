package com.bxvip.lottery007.ui.activity;

import android.os.Bundle;
import android.os.Handler;

import com.bxvip.lottery007.R;
import com.bxvip.lottery007.base.BaseAppCompatActivity;
import com.lwh.jackknife.ioc.annotation.ContentView;
import com.lwh.jackknife.util.ToastUtils;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

@ContentView(R.layout.activity_pick_city)
public class PickCityActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CityPicker.getInstance()
                .setFragmentManager(getSupportFragmentManager())	//此方法必须调用
                .enableAnimation(true)	//启用动画效果
                .setLocatedCity(new LocatedCity("北京", "北京", "101010100"))  //APP自身已定位的城市，默认为null（定位失败）
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City city) {
                        ToastUtils.showShort(PickCityActivity.this, city.getName());
                    }

                    @Override
                    public void onLocate() {
                        //开始定位，这里模拟一下定位
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //定位完成之后更新数据
                                CityPicker.getInstance()
                                        .locateComplete(new LocatedCity("深圳", "广东", "101280601"), LocateState.SUCCESS);
                            }
                        }, 2000);
                    }
                })
                .show();
    }
}
