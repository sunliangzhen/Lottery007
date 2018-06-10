package com.bxvip.lottery007.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;

public class FlipOverView extends android.support.v7.widget.AppCompatImageView {

    private int mFlipState;
    private int mAnimationDuration = 2000;

    public FlipOverView(Context context) {
        super(context);
    }

    public FlipOverView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlipOverView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAnimationDuration(int duration) {
        this.mAnimationDuration = duration;
    }

    public int getAnimationDuration() {
        return mAnimationDuration;
    }

    public void flipOver() {
        if (mFlipState ==  0) {
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(this, "rotationY", 0, -180f);
            ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(this, "alpha", 1f, 0.2f);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(objectAnimator1, objectAnimator2);
            set.setDuration(mAnimationDuration);
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    setClickable(false);// 防止用户多次点击
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    setClickable(true);
                }
            });
            set.start();
            mFlipState = 1;
        } else if (mFlipState == 1) {
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(this, "rotationY", -180f, 0f);
            ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(this, "alpha", 0.2f, 1f);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(objectAnimator1, objectAnimator2);
            set.setDuration(mAnimationDuration);
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    setClickable(false);// 这里的作用
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    setClickable(true);
                }
            });
            set.start();
            mFlipState = 2;
        } else if (mFlipState == 2) {
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(this, "rotationY", 0f, 180f);
            ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(this, "alpha", 1f, 0.2f);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(objectAnimator1, objectAnimator2);
            set.setDuration(mAnimationDuration);
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    setClickable(false);// 这里的作用
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    setClickable(true);
                }
            });
            set.start();
            mFlipState = 3;
        } else if (mFlipState == 3) {
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(this, "rotationY", 180f, 0f);
            ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(this, "alpha", 0.2f, 1f);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(objectAnimator1, objectAnimator2);
            set.setDuration(mAnimationDuration);
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    setClickable(false);// 这里的作用
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    setClickable(true);
                }
            });
            set.start();
            mFlipState = 0;
        }
    }
}
