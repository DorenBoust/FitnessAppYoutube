package com.example.fitnessapp.models;


import android.app.Notification;
import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.fitnessapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.fitnessapp.models.AppNotification.CHANNEL_2_ID;

public class NotificationDietThread extends Thread {

    private String mealName;
    private String mealTime;
    private Context context;
    private NotificationManagerCompat notificationManager;

    public NotificationDietThread(Context context, NotificationManagerCompat notificationManager, String mealName, String mealTime){

        this.context = context;
        this.mealName = mealName;
        this.mealTime = mealTime;
        this.notificationManager = notificationManager;

    }

    @Override
    public void run() {

        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String correctTime = timeFormat.format(new Date());

        if (correctTime.equals(mealTime)){

            notification(mealTime,mealName);

            return;

        }

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        run();

    }


    private void notification(String mealTime, String mealName){

        String contentText = mealTime + " - " + mealName + " -" + " הגיע הזמן לאכול ";

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_notification_ex_timer)
                .setContentText(contentText)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(1, notification);
    }
}



