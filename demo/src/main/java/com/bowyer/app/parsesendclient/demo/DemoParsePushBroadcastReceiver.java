package com.bowyer.app.parsesendclient.demo;

import com.google.gson.Gson;

import com.bowyer.app.parsesendclient.demo.model.ParsePushModel;
import com.parse.ParsePushBroadcastReceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

/**
 * Created by Bowyer on 2015/08/02.
 */
public class DemoParsePushBroadcastReceiver extends ParsePushBroadcastReceiver {

    private ParsePushModel mPushModel;
    private int VLUME_SIZE = 2;//sound size

    public void onPushReceive(final Context context, Intent intent) {
        Bundle extra = intent.getExtras();
        String data = extra.getString("com.parse.Data");
        mPushModel = new Gson().fromJson(data, ParsePushModel.class);

        Notification notification = generateNotification(context, intent.getExtras());
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(context.getPackageName(), 1,
                notification);
    }


    public Notification generateNotification(Context context, Bundle extras) {

        PackageManager packageManager = context.getPackageManager();

        int icon = 0;
        String title = mPushModel.getTitle();
        boolean forceSound = mPushModel.forceSound();

        if (forceSound) {
            //if true force sound.
            AudioManager manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

            int maxvolume = manager.getStreamMaxVolume(AudioManager.STREAM_RING);

            manager.setStreamVolume(AudioManager.STREAM_RING, maxvolume / VLUME_SIZE,
                    AudioManager.FLAG_VIBRATE);
        }
        try {
            ApplicationInfo applicationInfo = packageManager
                    .getApplicationInfo(context.getPackageName(), 0);
            icon = packageManager.getApplicationInfo(context.getPackageName(), 0).icon;
            if (TextUtils.isEmpty(title)) {
                title = packageManager.getApplicationLabel(applicationInfo).toString();
            }
        } catch (PackageManager.NameNotFoundException e) {
        }

        String message = mPushModel.getMessage();

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtras(extras);

        PendingIntent pendingIntent = PendingIntent
                .getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(title);
        builder.setSmallIcon(icon);
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setContentIntent(pendingIntent);
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(true);

        builder.setDefaults(Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND);
        return builder.build();

    }
}
