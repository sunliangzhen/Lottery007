package com.bxvip.lottery007.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * 吞没事件的相对布局。
 */
public class SwallowedEventRelativeLayout extends RelativeLayout {

    public SwallowedEventRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SwallowedEventRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwallowedEventRelativeLayout(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
