package com.example.medic3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context, "notifyRaminapp")
                .setSmallIcon(R.drawable.pill)
                .setContentTitle("Medic pill reminder")
                .setContentText("remember your medication")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager=NotificationManagerCompat.from(context);
        notificationManager.notify(200, builder.build());

    }
}