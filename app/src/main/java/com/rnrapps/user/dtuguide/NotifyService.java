package com.rnrapps.user.dtuguide;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 25-07-2016.
 */
public class NotifyService extends Service{

    private String URL_FEED = "https://graph.facebook.com/382057938566656/feed?fields=id,full_picture,message,story,created_time,link&access_token=1610382879221507|eQEEkGV4wk9PHCBzrw9Whbdzyuc";
    private FeedItem item;
    private String timestamp;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Intent notIntent = new Intent(this, Main2Activity.class);
        notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        timestamp=intent.getStringExtra("timestamp");

        getFeed();

        //if(timestamp.equals(item.getTimeStamp()))
          //  stopSelf();

        Notification notification = builder.setContentTitle("DTU Guide")
                .setContentText("DCE Speaks Up \n"+item.getStatus())
                .setDefaults(Notification.DEFAULT_SOUND)
                .setSmallIcon(R.drawable.dtulogo)
                .setColor(getResources().getColor(R.color.colorPrimary))
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
        stopSelf();
        return START_NOT_STICKY;
    }

    public void getFeed(){
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                URL_FEED, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                if (response != null) {
                    parseJsonFeed(response);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("here","no internet");
                stopSelf();
            }
        });
    }

    private void parseJsonFeed(final JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("data");
            int i = 0;
            JSONObject feedObj = (JSONObject) feedArray.get(i);

            item = new FeedItem();
            item.setId(feedObj.getString("id"));

            String image = feedObj.isNull("full_picture") ? null : feedObj
                    .getString("full_picture");
            item.setImge(image);

            if(feedObj.opt("message")!=null)
                item.setStatus(feedObj.getString("message"));
            else
                item.setStatus(feedObj.getString("story"));

            item.setTimeStamp(feedObj.getString("created_time"));
         } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        Intent intent=new Intent(getApplicationContext(), NotifyService.class);
        if(item!=null)
            intent.putExtra("timestamp",item.getTimeStamp());
        else
            intent.putExtra("timestamp",timestamp);
        alarm.set(
                alarm.RTC_WAKEUP,
                System.currentTimeMillis() + (1000 * 60*2),
                PendingIntent.getService(getApplicationContext(), 0, intent, 0)
        );
    }
}
