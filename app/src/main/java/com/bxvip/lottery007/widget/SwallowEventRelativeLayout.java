package com.bxvip.lottery007.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class SwallowEventRelativeLayout extends RelativeLayout {

    public SwallowEventRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SwallowEventRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwallowEventRelativeLayout(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
