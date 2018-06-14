package com.bxvip.lottery007.bean.json;

public interface Weather {

    String getDate();   //获得日期
    String getSunrise();//获得日出时间
    String getSunset();//获得日落时间
    String getHigh();//获得最高气温
    String getLow();//获得最低气温
    int getAqi();//获得空气质量指数
    String getFx();//获得风向
    String getFl();//获得风力
    String getType();//获得天气类型
    String getNotice();//获得温馨小提示
}
