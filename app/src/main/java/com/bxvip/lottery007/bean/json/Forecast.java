package com.bxvip.lottery007.bean.json;

/**
 * 天气预报
 */
public class Forecast implements Weather {

    /**
     * 日期
     */
    private String date;

    /**
     * 日出时间
     */
    private String sunrise;

    /**
     * 最高气温
     */
    private String high;

    /**
     * 最低气温
     */
    private String low;

    /**
     * 日落时间
     */
    private String sunset;

    /**
     * 空气质量指数AQI
     */
    private int aqi;

    /**
     * 风向
     */
    private String fx;

    /**
     * 风力
     */
    private String fl;

    /**
     * 天气的类型
     */
    private String type;

    /**
     * 温馨小提示
     */
    private String notice;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getHigh() {
        return high;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getLow() {
        return low;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSunset() {
        return sunset;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public int getAqi() {
        return aqi;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFx() {
        return fx;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getFl() {
        return fl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getNotice() {
        return notice;
    }
}