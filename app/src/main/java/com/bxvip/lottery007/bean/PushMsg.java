package com.bxvip.lottery007.bean;

import com.lwh.jackknife.orm.OrmTable;
import com.lwh.jackknife.orm.constraint.AssignType;
import com.lwh.jackknife.orm.constraint.NotNull;
import com.lwh.jackknife.orm.constraint.PrimaryKey;
import com.lwh.jackknife.orm.table.Column;

public class PushMsg implements OrmTable {

    @PrimaryKey(AssignType.BY_MYSELF)
    @Column("save_time")
    private long time;

    @NotNull
    private String content;

    public PushMsg() {
    }

    public PushMsg(long time, String content) {
        this.time = time;
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    @Override
    public <T> T getPrimaryKeyValue() {
        return (T) new Long(time);
    }

    @Override
    public boolean isUpgradeRecreated() {
        return false;
    }
}
