package com.bxvip.lottery007.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.bxvip.app.dafa.lib.Dafa;
import com.bxvip.app.dafa.lib.callback.LoadCallback;
import com.bxvip.lottery007.R;
import com.bxvip.lottery007.base.BaseAppCompatActivity;
import com.lwh.jackknife.ioc.annotation.ContentView;

import java.io.IOException;

@ContentView(R.layout.activity_splash)
public class SplashActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!Dafa.launchDafaApp(this)) {  //如果没有安装大发彩票APP
            Dafa.loadAPI(this, new LoadCallback() { //加载大发的API
                @Override
                public void onStopLoading() { //开关返回0，会走这个回调
                    startMainActivity();
                }

                @Override
                public void onLoadFailure(IOException e) {  //加载出错，会走这个回调
                    startMainActivity();
                }
            });
        } else {
            finish(); //启动大发APP成功，销毁当前界面
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class); //主界面为你自己的主界面
        startActivity(intent);
        finish();
    }
}
