package com.bxvip.lottery007.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bxvip.lottery007.R;
import com.bxvip.lottery007.base.BaseAppCompatActivity;
import com.bxvip.lottery007.bean.json.JSON_Weather;
import com.bxvip.lottery007.bean.json.WeatherData;
import com.bxvip.lottery007.bean.json.Yesterday;
import com.google.gson.Gson;
import com.lwh.jackknife.ioc.annotation.ContentView;
import com.lwh.jackknife.ioc.annotation.OnClick;
import com.lwh.jackknife.ioc.annotation.ViewInject;
import com.lwh.jackknife.util.Logger;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

@ContentView(R.layout.activity_weather)
public class WeatherActivity extends BaseAppCompatActivity {

    @ViewInject(R.id.tv_weather_temperature)
    private TextView tv_weather_temperature;

    @ViewInject(R.id.tv_weather_desc)
    private TextView tv_weather_desc;

    @ViewInject(R.id.tv_weather_notice)
    private TextView tv_weather_notice;

    @ViewInject(R.id.tv_weather_humidity)
    private TextView tv_weather_humidity;

    @ViewInject(R.id.tv_weather_pm25)
    private TextView tv_weather_pm25;

    @ViewInject(R.id.tv_weather_pm100)
    private TextView tv_weather_pm100;

    @ViewInject(R.id.rv_weather)
    private RecyclerView rv_weather;

    @OnClick(R.id.iv_weather_back)
    public void onBack(View view) {
        finish();
    }

    @OnClick(R.id.tv_weather_locatedcity)
    public void chooseCity(View view) {
        Intent intent = new Intent(this, PickCityActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://www.sojson.com/open/api/weather/json.shtml?city=北京")
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
            }
        });
    }
}
