package com.bxvip.lottery007.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bxvip.lottery007.R;
import com.bxvip.lottery007.base.BaseAppCompatActivity;
import com.lwh.jackknife.ioc.annotation.ContentView;
import com.lwh.jackknife.ioc.annotation.OnClick;
import com.lwh.jackknife.ioc.annotation.ViewInject;
import com.lwh.jackknife.util.CacheUtils;
import com.lwh.jackknife.util.ToastUtils;

@ContentView(R.layout.activity_profile)
public class ProfileActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @ViewInject(R.id.rl_profile_clearcache)
    RelativeLayout rl_profile_clearcache;

    @ViewInject(R.id.rl_profile_checkupdate)
    RelativeLayout rl_profile_checkupdate;

    @ViewInject(R.id.rl_profile_feedback)
    RelativeLayout rl_profile_feedback;

    @ViewInject(R.id.rl_profile_share)
    RelativeLayout rl_profile_share;

    @ViewInject(R.id.rl_profile_pushmsg)
    RelativeLayout rl_profile_pushmsg;

    @ViewInject(R.id.rl_profile_exit)
    RelativeLayout rl_profile_exit;

//    LinearLayout ll_qqlogin;

    @OnClick(R.id.iv_profile_back)
    public void onBack(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ll_qqlogin.setOnClickListener(this);
        rl_profile_clearcache.setOnClickListener(this);
        rl_profile_checkupdate.setOnClickListener(this);
        rl_profile_feedback.setOnClickListener(this);
        rl_profile_share.setOnClickListener(this);
        rl_profile_pushmsg.setOnClickListener(this);
        rl_profile_exit.setOnClickListener(this);
    }

    public void qqLogin(){
//        final Tencent tencent = Tencent.createInstance("1106932262", this);
//        if(!tencent.isSessionValid()) {
//            tencent.login(this, "all", new IUiListener() {
//                @Override
//                public void onComplete(Object o) {
//                    JSONObject jsonObject = (JSONObject)o;
//                    //设置openid和token，否则获取不到下面的信息
//                    new LoginUtils(ProfileActivity.this, tencent).initOpenidAndToken(jsonObject.toString());
//                }
//
//                @Override
//                public void onError(UiError uiError) {
//                    Toast.makeText(ProfileActivity.this, "授权失败",Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onCancel() {
//                    Toast.makeText(ProfileActivity.this, "取消授权",Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
//            case R.id.ll_qqlogin:
//                qqLogin();
//                break;
            case R.id.rl_profile_clearcache:
                CacheUtils.clearAllCache(this);
                ToastUtils.showShort(this, "缓存已清除");
                break;
            case R.id.rl_profile_checkupdate:
                ToastUtils.showShort(this, "已最新");
                break;
            case R.id.rl_profile_feedback:
                intent.setClass(this, FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_profile_share:
                toast("分享失败");
                //分享
//                OnekeyShare oks = new OnekeyShare();
//                //关闭sso授权
//                oks.disableSSOWhenAuthorize();
//
//                // title标题，微信、QQ和QQ空间等平台使用
//                oks.setTitle("分享");
//                // titleUrl QQ和QQ空间跳转链接
//                oks.setTitleUrl("http://www.jackwhliu.cn");
//                // text是分享文本，所有平台都需要这个字段
//                oks.setText("我是分享文本");
//                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//                oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//                // url在微信、微博，Facebook等平台中使用
//                oks.setUrl("http://www.jackwhliu.cn");
//                // comment是我对这条分享的评论，仅在人人网使用
//                oks.setComment("我是测试评论文本");
//                // 启动分享GUI
//                oks.show(getContext());
                break;
            case R.id.rl_profile_pushmsg:
                intent.setClass(this, PushMsgActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_profile_exit:
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
        }
    }
}
