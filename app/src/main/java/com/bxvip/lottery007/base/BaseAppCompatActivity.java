package com.bxvip.lottery007.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lwh.jackknife.app.Application;
import com.lwh.jackknife.ioc.SupportActivity;
import com.lwh.jackknife.ioc.ViewInjector;
import com.lwh.jackknife.util.ToastUtils;

public class BaseAppCompatActivity extends AppCompatActivity implements SupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjector.inject(this);
        this.push();
    }

    protected void toast(String tips) {
        ToastUtils.showShort(this, tips);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.pop();
    }

    private void push() {
        if (this.getApplication() instanceof Application) {
            Application.getInstance().pushTask(this);
        }
    }

    private void pop() {
        if (this.getApplication() instanceof Application) {
            Application.getInstance().popTask();
        }
    }

    @Override
    public View findViewById(int id) {
        return super.findViewById(id);
    }
}
