package com.bxvip.lottery007.adapter;

import android.content.Context;
import android.widget.TextView;

import com.bxvip.lottery007.R;
import com.bxvip.lottery007.bean.json.Weather;

import java.util.ArrayList;

import cn.jackwhliu.rvadapter.lib.BaseRVAdapter;

/**
 * 天气适配器。
 *
 * @param <W>
 */
public class WeatherAdapter<W extends Weather> extends BaseRVAdapter<W> {

    public WeatherAdapter(Context context, ArrayList<W> datas) {
        super(context, datas);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int pos, W weather) {
        TextView tv_weather_date = (TextView) holder.findViewById(R.id.tv_weather_date);
        TextView tv_weather_sunrise = (TextView) holder.findViewById(R.id.tv_weather_sunrise);
        TextView tv_weather_sunset = (TextView) holder.findViewById(R.id.tv_weather_sunset);
        TextView tv_weather_high = (TextView) holder.findViewById(R.id.tv_weather_high);
        TextView tv_weather_low = (TextView) holder.findViewById(R.id.tv_weather_low);
        TextView tv_weather_aqi = (TextView) holder.findViewById(R.id.tv_weather_aqi);
        TextView tv_weather_fx = (TextView) holder.findViewById(R.id.tv_weather_fx);
        TextView tv_weather_fl = (TextView) holder.findViewById(R.id.tv_weather_fl);
        TextView tv_weather_type = (TextView) holder.findViewById(R.id.tv_weather_type);
        TextView tv_weather_notices = (TextView) holder.findViewById(R.id.tv_weather_notice2);
        tv_weather_date.setText(weather.getDate());
        tv_weather_sunrise.setText("日出："+weather.getSunrise());
        tv_weather_sunset.setText("日落："+weather.getSunset());
        tv_weather_high.setText(weather.getHigh());
        tv_weather_low.setText(weather.getLow());
        tv_weather_aqi.setText("AQI："+weather.getAqi());
        tv_weather_fx.setText("风向："+weather.getFx());
        tv_weather_fl.setText("风力："+weather.getFl());
        tv_weather_type.setText(weather.getType());
        tv_weather_notices.setText(weather.getNotice());
    }

    @Override
    protected int[] getItemViewIds() {
        return new int[] {
                R.id.tv_weather_date,
                R.id.tv_weather_sunrise,
                R.id.tv_weather_sunset,
                R.id.tv_weather_high,
                R.id.tv_weather_low,
                R.id.tv_weather_aqi,
                R.id.tv_weather_fx,
                R.id.tv_weather_fl,
                R.id.tv_weather_type,
                R.id.tv_weather_notice2
        };
    }

    @Override
    protected int getItemId() {
        return R.layout.item_weather;
    }
}
