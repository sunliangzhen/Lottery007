package com.bxvip.lottery007.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bxvip.lottery007.R;

public class NoDataView extends RelativeLayout {

    public NoDataView(Context context) {
        super(context);
    }

    public NoDataView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoDataView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        TextView tv = new TextView(getContext());
        LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        tv.setLayoutParams(lp);
        tv.setText("暂无天气数据");
        tv.setTextColor(getResources().getColor(R.color.gray));
        addView(tv);
    }
}
