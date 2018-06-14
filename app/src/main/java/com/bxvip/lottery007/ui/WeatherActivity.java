package com.bxvip.lottery007.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bxvip.lottery007.Manifest;
import com.bxvip.lottery007.R;
import com.bxvip.lottery007.adapter.WeatherAdapter;
import com.bxvip.lottery007.base.BaseAppCompatActivity;
import com.bxvip.lottery007.bean.json.Forecast;
import com.bxvip.lottery007.bean.json.JSON_Weather;
import com.bxvip.lottery007.bean.json.WeatherData;
import com.bxvip.lottery007.bean.json.Yesterday;
import com.bxvip.lottery007.constant.UrlConstants;
import com.bxvip.lottery007.permission.PermissionHelper;
import com.bxvip.lottery007.permission.PermissionInterface;
import com.google.gson.Gson;
import com.lwh.jackknife.ioc.annotation.ContentView;
import com.lwh.jackknife.ioc.annotation.OnClick;
import com.lwh.jackknife.ioc.annotation.ViewInject;
import com.lwh.jackknife.util.ToastUtils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import java.io.IOException;
import java.util.ArrayList;

@ContentView(R.layout.activity_weather)
public class WeatherActivity extends BaseAppCompatActivity implements UrlConstants, PermissionInterface {

    @ViewInject(R.id.tv_weather_temperature)
    private TextView tv_weather_temperature;

    @ViewInject(R.id.tv_weather_desc)
    private TextView tv_weather_desc;

    @ViewInject(R.id.tv_weather_notice2)
    private TextView tv_weather_notice2;

    @ViewInject(R.id.tv_weather_humidity)
    private TextView tv_weather_humidity;

    @ViewInject(R.id.tv_weather_pm25)
    private TextView tv_weather_pm25;

    @ViewInject(R.id.tv_weather_pm100)
    private TextView tv_weather_pm100;

    @ViewInject(R.id.rv_weather)
    private RecyclerView rv_weather;

    @ViewInject(R.id.tv_weather_aqi)
    private TextView tv_weather_aqi;

    @ViewInject(R.id.tv_weather_date)
    private TextView tv_weather_date;

    @ViewInject(R.id.tv_weather_fx)
    private TextView tv_weather_fx;

    @ViewInject(R.id.tv_weather_fl)
    private TextView tv_weather_fl;

    @ViewInject(R.id.tv_weather_type)
    private TextView tv_weather_type;

    @ViewInject(R.id.tv_weather_high)
    private TextView tv_weather_high;

    @ViewInject(R.id.tv_weather_low)
    private TextView tv_weather_low;

    @ViewInject(R.id.tv_weather_notice)
    private TextView tv_weather_notice;

    @ViewInject(R.id.tv_weather_sunrise)
    private TextView tv_weather_sunrise;

    @ViewInject(R.id.tv_weather_sunset)
    private TextView tv_weather_sunset;

    @ViewInject(R.id.tv_weather_locatedcity)
    private TextView tv_weather_locatedcity;

    WeatherAdapter mAdapter;
    LocationClient mLocClient;
    private static final int REQUEST_PICK_CITY = 0x01;

    private String mCityName = "北京";

    private String mLocatedCity = "未知城市";

    private PermissionHelper mPermissionHelper;

    @OnClick(R.id.iv_weather_back)
    public void onBack(View view) {
        finish();
    }

    @OnClick(R.id.ll_weather_locatedcity)
    public void chooseCity(View view) {
        CityPicker.getInstance()
                .setFragmentManager(getSupportFragmentManager())	//此方法必须调用
                .enableAnimation(true)	//启用动画效果
                .setLocatedCity(new LocatedCity(mLocatedCity, "", ""))  //APP自身已定位的城市，默认为null（定位失败）
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City city) {
                        if (city != null) {
                            mCityName = city.getName();
                            tv_weather_locatedcity.setText(mCityName);
                        } else {
                            ToastUtils.showShort(WeatherActivity.this, "用户取消");
                        }
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

    @Override
    protected void onStart() {
        super.onStart();
        mPermissionHelper = new PermissionHelper(this, this);
        mPermissionHelper.requestPermissions();
        refreshWeather();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(mPermissionHelper.requestPermissionsResult(requestCode, permissions, grantResults)){
            //权限请求结果，并已经处理了该回调
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void refreshWeather() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL_WEATHER_PREFFIX+mCityName)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                JSON_Weather json_weather = gson.fromJson(json, JSON_Weather.class);
                int status = json_weather.getStatus();
                if (status == 200) {
                    WeatherData data = json_weather.getData();
                    loadData(data);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rv_weather.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv_weather.setItemAnimator(new DefaultItemAnimator());
        rv_weather.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                mLocatedCity = bdLocation.getCity();
                tv_weather_locatedcity.setText(mLocatedCity);
                ToastUtils.showShort(WeatherActivity.this, "定位到的城市"+mLocatedCity);
            }
        });
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    public void loadData(final WeatherData data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String temperature = data.getWendu();
                String quality = data.getQuality();
                String notice = data.getGanmao();
                String humidity = data.getShidu();
                int pm25 = data.getPm25();
                int pm10 = data.getPm10();
                Yesterday yesterday = data.getYesterday();
                int aqi = yesterday.getAqi();
                String date = yesterday.getDate();
                String fx = yesterday.getFx();
                String fl = yesterday.getFl();
                String type = yesterday.getType();
                String high = yesterday.getHigh();
                String low = yesterday.getLow();
                String notice2 = yesterday.getNotice();
                String sunrise = yesterday.getSunrise();
                String sunset = yesterday.getSunset();
                tv_weather_temperature.setText(temperature+"℃");
                tv_weather_desc.setText(quality);
                tv_weather_notice.setText(notice);
                tv_weather_humidity.setText("湿度："+humidity);
                tv_weather_pm25.setText("PM2.5："+pm25);
                tv_weather_pm100.setText("PM10："+pm10);
                tv_weather_aqi.setText("AQI："+aqi);
                tv_weather_date.setText(date);
                tv_weather_fx.setText("风向："+fx);
                tv_weather_fl.setText("风力："+fl);
                tv_weather_type.setText(type);
                tv_weather_high.setText(high);
                tv_weather_low.setText(low);
                tv_weather_notice2.setText(notice2);
                tv_weather_sunrise.setText("日出："+sunrise);
                tv_weather_sunset.setText("日落："+sunset);
                ArrayList<Forecast> forecasts = (ArrayList<Forecast>) data.getForecast();
                mAdapter = new WeatherAdapter(WeatherActivity.this, forecasts);
                rv_weather.setAdapter(mAdapter);
                mAdapter.addItems(forecasts);
            }
        });
    }

    @Override
    public int getPermissionsRequestCode() {
        return 10000;
    }

    @Override
    public String[] getPermissions() {
        return new String[]{
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
        };
    }

    @Override
    public void requestPermissionsSuccess() {
        refreshWeather();
    }

    @Override
    public void requestPermissionsFail() {
        finish();
    }
}
