package g.com.atvu;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.core.app.TaskStackBuilder;
import androidx.lifecycle.ViewModelProviders;


public class AlarmReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "g.com.atvu.channelId";
    private HabitViewModel habitViewModel;
    Context context;

    public AlarmReceiver(){

    }

    public AlarmReceiver(Context context){
        this.context=context;
    }

    @Override
    public void onReceive(Context context, Intent ıntent) {

        SharedPreferences sharedPref = context.getSharedPreferences("sharedPreferences",Context.MODE_PRIVATE);

        String habitTitle=sharedPref.getString("habitTitle","ErrorTitle");
        String habitDesc=sharedPref.getString("habitDesc","ErrorDesc");
        Log.d("shared",habitTitle);
        Intent notificationIntent = new Intent(context, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(context);

        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder
                    .setContentTitle("Koşu yap!")
                    .setContentText("Günde en az 2 km koşu yap!")
                    .setTicker("New Message Alert!")
                    .setContentIntent(pendingIntent)
                    .addAction(R.drawable.checked32,"Evet",pendingIntent)
                    .addAction(R.drawable.error32,"Hayır",pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent).getNotification();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID);
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "NotificationDemo",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notification);
    }


}
