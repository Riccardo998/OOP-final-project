package com.example.organizzatore.ui.ThingsToDo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.organizzatore.R;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper mNotificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = mNotificationHelper.getChannelNotification();
        mNotificationHelper.getManager().notify(1, nb.build());
    }
}
