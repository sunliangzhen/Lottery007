package com.bxvip.lottery007.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bxvip.lottery007.R;
import com.bxvip.lottery007.base.BaseAppCompatActivity;
import com.bxvip.lottery007.bean.Feedback;
import com.lwh.jackknife.ioc.annotation.ContentView;
import com.lwh.jackknife.ioc.annotation.ViewInject;
import com.lwh.jackknife.util.TextUtils;

import cn.bmob.v3.listener.SaveListener;

@ContentView(R.layout.activity_feedback)
public class FeedbackActivity extends BaseAppCompatActivity {

    @ViewInject(R.id.iv_feedback_back)
    ImageView iv_feedback_back;

    @ViewInject(R.id.et_feedback_message)
    EditText et_feedback_message;

    @ViewInject(R.id.et_feedback_contact)
    EditText et_feedback_contact;

    @ViewInject(R.id.btn_feedback_commit)
    Button btn_feedback_commit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iv_feedback_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_feedback_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = et_feedback_message.getText().toString();
                String contact = et_feedback_contact.getText().toString();
                if (TextUtils.isNotEmpty(message)) {
                    Feedback feedback = new Feedback(message, contact);
                    feedback.save(FeedbackActivity.this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            toast("反馈成功");
                            finish();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            toast("反馈失败，"+s);
                        }
                    });
                } else {
                    toast("内容不能为空");
                }
            }
        });
    }
}
