package com.example.saurabh.notify;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class Notification extends NotificationListenerService{

    Context context;

    @Override
    public void onCreate() {

        super.onCreate();
        context = getApplicationContext();

        Log.i("In oncreate","eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        String pack = sbn.getPackageName();
        String ticker = sbn.getNotification().tickerText.toString();
        Bundle extras = sbn.getNotification().extras;
        Log.i("ssssssssssssssss",extras.toString());
        String title = extras.getString("android.title");
        String text = extras.getCharSequence("android.text").toString();

        Log.i("----","_________________________________________________________");
        Log.i("Package", pack);
        Log.i("Ticker",ticker);
        Log.i("Title",title);
        Log.i("Text",text);

        Intent msgrcv = new Intent("Msg");
        msgrcv.putExtra("package", pack);
        msgrcv.putExtra("ticker", ticker);
        msgrcv.putExtra("title", title);
        msgrcv.putExtra("text", text);

        LocalBroadcastManager.getInstance(context).sendBroadcast(msgrcv);


    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("Msg","Notification Removed");

    }

}