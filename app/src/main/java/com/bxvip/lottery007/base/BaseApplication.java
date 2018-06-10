package com.bxvip.lottery007.base;

import android.app.Application;

import com.bxvip.lottery007.bean.PushMsg;
import com.lwh.jackknife.orm.Orm;
import com.lwh.jackknife.orm.OrmConfig;
import com.lwh.jackknife.util.Logger;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义APP的入口，用于初始化一些配置。
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.open();//打开日志管理系统
        initDb();
        initSdk();
    }

    private void initDb() {
        OrmConfig config = new OrmConfig.Builder()
                .database("lottery")
                .version(1)
                .tables(PushMsg.class)
                .build();
        Orm.init(this, config);
    }

    private void initSdk() {
        initJPushSdk();
    }

    private void initJPushSdk() {
        JPushInterface.init(this);  // 极光推送初始化
        JPushInterface.setDebugMode(true);  // 极光推送调试模式开启
    }
}
