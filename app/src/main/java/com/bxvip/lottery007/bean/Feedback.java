package com.bxvip.lottery007.bean;

import cn.bmob.v3.BmobObject;

public class Feedback extends BmobObject {

    private String msg;
    private String contact;

    public Feedback(String msg, String contact) {
        this.msg = msg;
        this.contact = contact;
    }

    public String getMsg() {
        return msg;
    }

    public String getContact() {
        return contact;
    }
}
