package com.cityogsadana.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.cityogsadana.R;
import com.cityogsadana.activity.LevelActivity_;
import com.cityogsadana.bean.NotificationBean;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.utils.AccountChecker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by system2 on 2/15/2018.
 */

public class AlarmReceiver extends BroadcastReceiver {

    int requestID = (int) System.currentTimeMillis();
    private Context context;
    private UserBean user;
    private NotificationBean notificationBean;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        user = UserPref.getUser(context);
        notificationBean = UserPref.getNotificationBean(context);

        Date currentTime = Calendar.getInstance().getTime();
        DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
        String currentTimeStr = df1.format(currentTime);
        boolean checkMorining = AccountChecker.checkMorning(currentTimeStr);
        boolean checkEvening = AccountChecker.checkEvening(currentTimeStr);

        if (user.getUser_id() != null) {
            if(!user.getLevel().getUserLevel().equalsIgnoreCase("6"))
            if (checkMorining && checkEvening) {
                if(notificationBean.isEveNoti()==false){
                    sendNotification("Keep yourself motivated. Have a good night sleep.");
                    notificationBean.setEveNoti(true);
                    notificationBean.setMorningNoti(false);
                    UserPref.updateNotification(context,notificationBean);
                    notificationBean = UserPref.getNotificationBean(context);
                }


            } else {
                if (checkMorining) {
                    if(notificationBean.isMorningNoti()==false){
                        sendNotification("Good morning." + user.getName() + " !.Have a great Day ahead. Keep following the routine.");
                        notificationBean.setEveNoti(false);
                        notificationBean.setMorningNoti(true);
                        UserPref.updateNotification(context,notificationBean);
                        notificationBean = UserPref.getNotificationBean(context);
                    }


                }
            }
        }


        Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();
    }


    private void sendNotification(String messageBody) {

        Intent intent = new Intent(context, LevelActivity_.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestID, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.yoga)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setContentTitle("Chityog")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(requestID /* ID of notification */, notificationBuilder.build());
    }
}
