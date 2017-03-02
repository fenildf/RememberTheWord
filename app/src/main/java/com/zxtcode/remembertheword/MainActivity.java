package com.zxtcode.remembertheword;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.GridViewPager;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends WearableActivity {

    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private static final String EXTRA_EVENT_ID = "ExtraEventId";

    private BoxInsetLayout mContainerView;
    private GridViewPager mViewPager;
    private TextView mBtnNofity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
        mViewPager = (GridViewPager) findViewById(R.id.remember_word_viewpager);
        mBtnNofity = (TextView) findViewById(R.id.remember_word_notification);
        mBtnNofity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int notificationId = 001;
                // Build intent for notification content
                Intent viewIntent = new Intent(MainActivity.this, RememberWordActivity.class);
                viewIntent.putExtra(EXTRA_EVENT_ID, 1000);
                PendingIntent viewPendingIntent = PendingIntent.getActivity(MainActivity.this, 0, viewIntent, 0);

                PendingIntent intent = PendingIntent.getBroadcast(MainActivity.this, notificationId, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);
                List<NotificationCompat.Action> actionList = new ArrayList<>();
                actionList.add(new NotificationCompat.Action(R.mipmap.ic_launcher, "记住", intent));
                actionList.add(new NotificationCompat.Action(R.mipmap.ic_launcher, "下次", intent));

                NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();
                wearableExtender.addActions(actionList);

                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(MainActivity.this)
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
                        NotificationManagerCompat.from(MainActivity.this);

                // Issue the notification with notification manager.
                notificationManager.notify(notificationId, notificationBuilder.build());
            }
        });
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {

    }
}
