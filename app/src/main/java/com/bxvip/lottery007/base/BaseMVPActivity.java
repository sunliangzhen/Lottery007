package com.bxvip.lottery007.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.lwh.jackknife.app.Application;
import com.lwh.jackknife.ioc.SupportActivity;
import com.lwh.jackknife.ioc.ViewInjector;
import com.lwh.jackknife.mvp.BasePresenter;
import com.lwh.jackknife.mvp.IBaseView;
import com.lwh.jackknife.util.ToastUtils;

public abstract class BaseMVPActivity<V extends IBaseView, P extends BasePresenter<V>>
        extends AppCompatActivity implements SupportActivity {

    protected P mPresenter;
    protected final String TAG = this.getClass().getSimpleName();

    protected abstract P createPresenter();

    protected void onResume() {
        Log.i(this.TAG, "onResume()");
        super.onResume();
    }

    protected void onPause() {
        Log.i(this.TAG, "onPause()");
        super.onPause();
    }

    protected void onStop() {
        Log.i(this.TAG, "onStop()");
        super.onStop();
    }

    protected void onStart() {
        Log.i(this.TAG, "onStart()");
        super.onStart();
    }

    protected void onDestroy() {
        Log.i(this.TAG, "onDestroy()");
        super.onDestroy();
        this.mPresenter.detachView();
        this.pop();
    }

    protected void toast(String tips) {
        ToastUtils.showShort(this, tips);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(this.TAG, "onCreate()");
        ViewInjector.inject(this);
        this.mPresenter = this.createPresenter();
        this.mPresenter.attachView((V) this);
        this.push();
    }

    public void setOrChangeTranslucentColor(Toolbar toolbar, View bottomNavigationBar, int translucentPrimaryColor){
        //判断版本,如果[4.4,5.0)就设置状态栏和导航栏为透明
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT< Build.VERSION_CODES.LOLLIPOP){
            if(toolbar!=null){
                //1.先设置toolbar的高度
                ViewGroup.LayoutParams params = toolbar.getLayoutParams();
                int statusBarHeight = getStatusBarHeight(this);
                params.height += statusBarHeight ;
                toolbar.setLayoutParams(params);
                //2.设置paddingTop，以达到状态栏不遮挡toolbar的内容。
                toolbar.setPadding(
                        toolbar.getPaddingLeft(),
                        toolbar.getPaddingTop()+getStatusBarHeight(this),
                        toolbar.getPaddingRight(),
                        toolbar.getPaddingBottom());
                //设置顶部的颜色
                toolbar.setBackgroundColor(translucentPrimaryColor);
            }
            if(bottomNavigationBar!=null){
                //解决低版本4.4+的虚拟导航栏的
                if(hasNavigationBarShow(getWindowManager())){
                    ViewGroup.LayoutParams p = bottomNavigationBar.getLayoutParams();
                    p.height += getNavigationBarHeight(this);
                    bottomNavigationBar.setLayoutParams(p);
                    //设置底部导航栏的颜色
                    bottomNavigationBar.setBackgroundColor(translucentPrimaryColor);
                }
            }
        }else if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setNavigationBarColor(translucentPrimaryColor);
            getWindow().setStatusBarColor(translucentPrimaryColor);
        }else{
            //<4.4的，不做处理
        }
    }

    private int getNavigationBarHeight(Context context) {
        return getSystemComponentDimen(this, "navigation_bar_height");
    }

    /**
     * 获取状态栏的高度
     * @param context
     * @return
     */
    private int getStatusBarHeight(Context context) {
        // 反射手机运行的类：android.R.dimen.status_bar_height.
        return getSystemComponentDimen(this, "status_bar_height");
    }

    private static int getSystemComponentDimen(Context context, String dimenName){
        // 反射手机运行的类：android.R.dimen.status_bar_height.
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            String heightStr = clazz.getField(dimenName).get(object).toString();
            int height = Integer.parseInt(heightStr);
            //dp--->px
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static boolean hasNavigationBarShow(WindowManager wm){
        Display display = wm.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        //获取整个屏幕的高度
        display.getRealMetrics(outMetrics);
        int heightPixels = outMetrics.heightPixels;
        int widthPixels = outMetrics.widthPixels;
        //获取内容展示部分的高度
        outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        int heightPixels2 = outMetrics.heightPixels;
        int widthPixels2 = outMetrics.widthPixels;
        int w = widthPixels-widthPixels2;
        int h = heightPixels-heightPixels2;
        System.out.println("~~~~~~~~~~~~~~~~h:"+h);
        return  w>0||h>0;//竖屏和横屏两种情况。
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
    public View findViewById(@IdRes int id) {
        return super.findViewById(id);
    }
}
