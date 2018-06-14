package com.bxvip.lottery007.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bxvip.lottery007.bean.PushMsg;
import com.lwh.jackknife.orm.dao.DaoFactory;
import com.lwh.jackknife.orm.dao.OrmDao;

import cn.jpush.android.api.JPushInterface;

/**
 * 极光推送的消息的接收者。
 */
public class PushReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            String content = bundle.getString(JPushInterface.EXTRA_ALERT);
            OrmDao<PushMsg> dao = DaoFactory.getDao(PushMsg.class);
            dao.insert(new PushMsg(System.currentTimeMillis(), content));
        }
    }
}
