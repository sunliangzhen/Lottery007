package com.bxvip.lottery007.bean.json;

import java.io.Serializable;

public class LotteryResult implements Serializable {

    private long timestamp;
    private String expect;
    private String time;
    private String name;
    private String code;
    private String openCode;

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getExpect() {
        return expect;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setOpenCode(String openCode) {
        this.openCode = openCode;
    }

    public String getOpenCode() {
        return openCode;
    }
}