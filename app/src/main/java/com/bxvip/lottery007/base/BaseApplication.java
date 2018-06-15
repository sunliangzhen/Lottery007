package com.bxvip.lottery007.base;

import android.app.Application;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.bxvip.lottery007.bean.PushMsg;
import com.lwh.jackknife.orm.Orm;
import com.lwh.jackknife.orm.OrmConfig;
import com.lwh.jackknife.util.Logger;

import cn.bmob.v3.Bmob;
import cn.jpush.android.api.JPushInterface;

/**
 * 自定义APP的入口，用于初始化一些配置。
 */
public class BaseApplication extends Application {

    LocationClient mLocClient;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.open();//打开日志管理系统
        initDb();//初始化数据库
        initSdk();//初始化第三方SDK
    }

    private void initDb() {
        OrmConfig config = new OrmConfig.Builder()
                .database("lottery")    //数据库的名称
                .version(1) //数据库的版本号
                .tables(PushMsg.class)  //要初始化的表
                .build();
        Orm.init(this, config);
    }

    private void initSdk() {
        initJPushSdk();
        initBaiduMapSdk();
        initBmobSdk();
    }

    private void initBmobSdk() {
        Bmob.initialize(this, "cf11ffc841657a55b82f03c0fc43f39a");
    }

    private void initBaiduMapSdk() {

    }

    private void initJPushSdk() {
        JPushInterface.init(this);  // 极光推送初始化
        JPushInterface.setDebugMode(true);  // 极光推送调试模式开启
    }
}
