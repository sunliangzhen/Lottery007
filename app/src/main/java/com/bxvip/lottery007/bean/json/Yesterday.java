package com.bxvip.lottery007.bean.json;

/**
 * 昨天的天气
 */
public class Yesterday {

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}