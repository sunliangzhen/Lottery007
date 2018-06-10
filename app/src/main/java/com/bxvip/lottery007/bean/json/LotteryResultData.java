package com.bxvip.lottery007.bean.json;

import java.util.List;

public class LotteryResultData {

    private List<LotteryResult> result;

    private int ret_code;

    public void setResult(List<LotteryResult> result) {
        this.result = result;
    }

    public List<LotteryResult> getResult() {
        return result;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public int getRet_code() {
        return ret_code;
    }
}