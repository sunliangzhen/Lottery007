package com.bxvip.lottery007.widget;

/**
 * 彩票球的数据
 */
public class LotteryBall {

    private int color;
    private String number;

    public LotteryBall() {}

    public LotteryBall(int color, String number) {
        this.color = color;
        this.number = number;
    }

    public int getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }
}
