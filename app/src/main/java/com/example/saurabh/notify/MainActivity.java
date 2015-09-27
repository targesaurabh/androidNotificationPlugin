package com.example.saurabh.notify;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.service.notification.StatusBarNotification;
import android.support.v4.content.LocalBroadcastManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends Activity {


    TextView tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Intent intent=new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        startActivity(intent);

        startService(new Intent(this, Notification.class));

        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("Msg"));
    }

    private BroadcastReceiver onNotice= new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {


            String pack = intent.getStringExtra("package");
            String title = intent.getStringExtra("title");
            String ticker = intent.getStringExtra("ticker");
            String text = intent.getStringExtra("text");

            tw = (TextView)findViewById(R.id.displayText);
            tw.setText(pack + " \n--\n " + title + " \n--\n " + ticker + " \n--\n " + text);
        }
    };

    public void sendNotification(View view){
        tw.setText("sent...");
        Intent msgrcv = new Intent("Msg");
        msgrcv.putExtra("package", "packagename");
        msgrcv.putExtra("ticker", "tickername");
        msgrcv.putExtra("title", "titlename");
        msgrcv.putExtra("text", "textname");

        Context context = getApplicationContext();
        LocalBroadcastManager.getInstance(context).sendBroadcast(msgrcv);
    }


}
