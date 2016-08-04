package com.rnrapps.user.dtuguide;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.rnrapps.user.dtuguide.CollegeMap.CampusMap;
import com.rnrapps.user.dtuguide.DceSpeaksUp.Main2Activity;
import com.rnrapps.user.dtuguide.ImpContacts.SocietyActivity;
import com.rnrapps.user.dtuguide.Notes.NotesActivity;
import com.rnrapps.user.dtuguide.TimeTables.TimetableActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rohanagarwal94 on 20/7/16.
 */
public class Utils {
    private Utils() {
    }

    public static String getTimeFromTimestamp(String a)
    {
        String timeAgo=null;
        a=a.replace("T"," ").replace("+",".");

        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Date timeCreatedDate = dateFormat.parse(a);
            timeAgo =(String) DateUtils.getRelativeTimeSpanString(
                    (timeCreatedDate.getTime()),
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeAgo;
    }

    public static void NavDrawer(MenuItem item, Context context)
    {
        int id = item.getItemId();

        if (id == R.id.nav_map) {
            Intent intent=new Intent(context,CampusMap.class);
            context.startActivity(intent);
        } else if (id == R.id.nav_societies) {
            Intent intent=new Intent(context,SocietyActivity.class);
            context.startActivity(intent);
        } else if (id == R.id.nav_notes) {
            Intent intent=new Intent(context,NotesActivity.class);
            context.startActivity(intent);
        } else if (id == R.id.nav_timetable) {
            Intent intent=new Intent(context,TimetableActivity.class);
            context.startActivity(intent);
        }else if (id == R.id.nav_dcespeaksup) {
            Intent intent=new Intent(context,Main2Activity.class);
            context.startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "https://play.google.com/store/apps/details?id=com.rnrapps.user.dtuguide&hl=en";
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
        } else if (id == R.id.nav_rate) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
        } else if (id == R.id.nav_about) {
            Intent intent=new Intent(context,AboutDevelopers.class);
            context.startActivity(intent);
        }
        else if(id == R.id.nav_feedback){
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "feedback");
            intent.setData(Uri.parse("mailto:dtuapp16@gmail.com"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }

    public static void configureNotificationService(Context context, String id)
    {
        SharedPreferences prefs = context.getSharedPreferences("notify", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("id",id);
        long launch_count = prefs.getLong("launch_count", 0) + 1;
        editor.putLong("launch_count", launch_count);
        editor.apply();
        if(launch_count==1){
            Log.d("main ","launching "+prefs.getString("id","00"));
            AlarmManager alarm = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
            Intent intent=new Intent(context, NotifyService.class);
            alarm.set(alarm.RTC_WAKEUP,System.currentTimeMillis() + 1000*60,
                    PendingIntent.getService(context, 0, intent,  PendingIntent.FLAG_UPDATE_CURRENT));
        }
    }

    //TODO: check this alternate implementation

//    public static void configureNotificationService(Context context, String id)
//    {
//        SharedPreferences prefs = context.getSharedPreferences("notify", 0);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString("id",id);
//        Boolean isFirstTime=!prefs.contains("app used");
//        editor.putLong("launch_count", launch_count);
//        editor.apply();
//        if(isFirstTime){
//            Log.d("main ","launching "+prefs.getString("id","00"));
//            AlarmManager alarm = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
//            Intent intent=new Intent(context, NotifyService.class);
//            alarm.set(alarm.RTC_WAKEUP,System.currentTimeMillis() + 1000*60,
//                    PendingIntent.getService(context, 0, intent,  PendingIntent.FLAG_UPDATE_CURRENT));
//        }
//       SharedPreferences.Editor editor = sharedpreferences.edit();
//       editor.putBoolean("app used", true);
//       editor.apply();
//    }

}
