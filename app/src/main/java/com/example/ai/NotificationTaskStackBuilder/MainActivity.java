package com.example.ai.NotificationTaskStackBuilder;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ai.navigationmap.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView=findViewById(R.id.text_main);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder=new NotificationCompat.Builder(MainActivity.this);
                Intent intent=new Intent(MainActivity.this,Notification.class);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
                stackBuilder.addParentStack(Notification.class);
                stackBuilder.addNextIntent(intent);

                PendingIntent pi = stackBuilder.getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                //PendingIntent pi=PendingIntent.getActivity(MainActivity.this,0,intent,0);
                NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);



                android.app.Notification notification=builder.setContentTitle("title")
                        .setContentText("content").setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentIntent(pi).build();

                manager.notify(0,notification);

            }
        });

    }
}
