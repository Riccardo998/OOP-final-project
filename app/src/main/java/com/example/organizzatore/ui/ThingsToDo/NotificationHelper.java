package com.example.organizzatore.ui.ThingsToDo;


import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.organizzatore.MainActivity;
import com.example.organizzatore.R;

public class NotificationHelper extends ContextWrapper {
    public static final String channelID="channelID";
    public static final String channelName="Channel 1";

    private NotificationManager mManager;

    public NotificationHelper(Context base){
        super(base);;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void createChannel(){
        NotificationChannel channel= new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        channel.enableLights(true);
        channel.setLightColor(R.color.colorPrimary);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager(){
        if(mManager==null){
            mManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification(){
        Intent resultIntent = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        resultIntent.putExtra("not", 1);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        return new NotificationCompat.Builder(this, channelID)
                .setAutoCancel(true)
                .setSubText(getString(R.string.notifica))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setSmallIcon(R.drawable.smallicon)
                .setContentTitle(getString(R.string.attivitafinita))
                .setContentText(getString(R.string.attivitafrase))
                .setDefaults(Notification.DEFAULT_ALL)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.unnamed))
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.unnamed))
                        .bigLargeIcon(null))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
    }
}
