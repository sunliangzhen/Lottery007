package com.bxvip.lottery007.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.bxvip.lottery007.R;
import com.lwh.jackknife.widget.animator.ActionWrapper;
import com.lwh.jackknife.widget.animator.AnimatorBuilder;
import com.lwh.jackknife.widget.animator.CubicTo;
import com.lwh.jackknife.widget.animator.RotateAction;
import com.lwh.jackknife.widget.animator.ScaleAction;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态的彩票控件
 */
public class DynamicLotteryView extends View {

    /**
     * 圆圈的半径。
     */
    private float mBallRadius;

    /**
     * 圆圈之间的间隙。
     */
    private float mBallSpan;

    /**
     * 圆圈中央文字的尺寸。
     */
    private float mBallTextSize;

    /**
     * 如果为第一个条目，一般设置特别的显示效果。
     */
    private boolean mCurrent;

    /**
     * 彩票中所有的数字。
     */
    private ArrayList<LotteryBall> mBalls = new ArrayList<>();

    /**
     * 用于绘制中央的文字。
     */
    private TextPaint mTextPaint;

    /**
     * 用于绘制圆圈。
     */
    private Paint mCirclePaint;

    public DynamicLotteryView(Context context) {
        this(context, null);
    }

    public DynamicLotteryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LotteryView);
        mBallRadius = a.getDimension(R.styleable.LotteryView_lotteryview_ballRadius, 20);
        mBallSpan = a.getDimension(R.styleable.LotteryView_lotteryview_ballSpan, 10);
        mBallTextSize = a.getDimension(R.styleable.LotteryView_lotteryview_ballTextSize, 15);
        mCurrent = a.getBoolean(R.styleable.LotteryView_lotteryview_isCurrent, true);
        a.recycle();
        initPaints();
    }

    private void initPaints() {
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextSize(mBallTextSize);
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.RED);
        mCirclePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth = measureWidth(widthMeasureSpec);
        int measuredHeight = measureHeight(heightMeasureSpec);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    private int measureWidth(int widthMeasureSpec) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        return size;
    }

    private int measureHeight(int heightMeasureSpec) {
        return (int) (2 * (mBallRadius+mBallSpan));
    }

    public void setBalls(ArrayList<LotteryBall> balls) {
        this.mBalls = balls;
        invalidate();
    }

    public void setCurrent(boolean current) {
        this.mCurrent = current;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBall(canvas);//绘制圆圈和圆圈中央的文字
    }

    public void updateStyle(float scale) {
        List<ScaleAction> actions = new ArrayList<>();
        List<RotateAction> rotateActions = new ArrayList<>();
        actions.add(new ScaleAction(scale, scale));
        rotateActions.add(new RotateAction(scale * 30));
        List<CubicTo> pathActons = new ArrayList<>();
        pathActons.add(new CubicTo(0, 100, 200, 300, 400, 500));
        ActionWrapper animator = new AnimatorBuilder()
                .rotate(rotateActions)
                .scale(actions)
                .path(pathActons)
                .build();
        animator.startAnimation(this, 2000);
    }

    private void drawBall(Canvas canvas) {
        int ballSize = mBalls.size();
        for (int i = 0; i < ballSize; i++) {
            LotteryBall currentBall = mBalls.get(i);
            int currentColor = currentBall.getColor();
            mTextPaint.setColor(Color.WHITE);//设置绘制指定文字的颜色
            mCirclePaint.setColor(currentColor);//将绘制圆圈的画笔置为黑色
            Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
            if (mCurrent) {
                float cx = (i + 1) * mBallSpan + mBallRadius + i * mBallRadius * 2;//圆心的x坐标
                float cy = mBallRadius + mBallSpan;//圆心的y坐标
                canvas.drawCircle(cx, cy, mBallRadius, mCirclePaint);
            }
            String currentNumber = currentBall.getNumber();
            float numberWidth = mTextPaint.measureText(currentNumber);
            float x = (i + 1) * mBallSpan + mBallRadius + i * mBallRadius * 2 - numberWidth / 2;
            float y = mBallRadius + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom + mBallSpan;
            canvas.drawText(currentNumber, x, y, mTextPaint);
        }
    }
}
