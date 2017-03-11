package com.zxtcode.remembertheword.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.zxtcode.remembertheword.activity.MainActivity;
import com.zxtcode.remembertheword.R;
import com.zxtcode.remembertheword.activity.RememberWordActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarent on 2017/3/11.
 */

public class NotificationUtils {

    private static final String EXTRA_EVENT_ID = "ExtraEventId";
    private static final int WORD_REQUEST_ID = 1000;
    private static final int WORD_NOTIFICATION_ID = 2000;

    public static void showWord(Context context) {
        // Build intent for notification content
        Intent viewIntent = new Intent(context, RememberWordActivity.class);
        viewIntent.putExtra(EXTRA_EVENT_ID, 1000);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(context, 0, viewIntent, 0);

        PendingIntent intent = PendingIntent.getBroadcast(
                context,
                WORD_REQUEST_ID,
                new Intent(context, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        PendingIntent nextTimeIntent = PendingIntent.getBroadcast(
                context,
                WORD_REQUEST_ID,
                new Intent(context, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        List<NotificationCompat.Action> actionList = new ArrayList<>();
        actionList.add(new NotificationCompat.Action(R.mipmap.ic_launcher, context.getString(R.string.notification_remember), intent));
        actionList.add(new NotificationCompat.Action(R.mipmap.ic_launcher, context.getString(R.string.notification_next_time), nextTimeIntent));

        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();
        wearableExtender.addActions(actionList);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("temperatures")
                        .setContentText(
                                "英 ['temprətʃəz]  美 ['temprətʃəz] \n" +
                                        "n.温度( temperature的名词复数 )； 气温； 高烧； 发烧")
                        .setContentIntent(viewPendingIntent)
                        .setVibrate(new long[]{100, 500, 100, 500})
                        .extend(wearableExtender);

        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(context);
        // Issue the notification with notification manager.
        notificationManager.notify(WORD_NOTIFICATION_ID, notificationBuilder.build());
    }

}
