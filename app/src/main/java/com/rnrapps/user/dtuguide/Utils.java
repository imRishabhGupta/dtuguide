package com.rnrapps.user.dtuguide;

import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;

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

}
