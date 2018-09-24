package com.example.trantien.theflashquiz.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Zuka on 9/18/18.
 */
public class Utils {
    public static String EXTRA_ROOM_NAME="EXTRA_ROOM_NAME";
    public static String KEY_FIREBASE="FROM_FIREBASE";
    public static String KEY_ANONYMOUS="FROM _ANONYMOUS";

    public static String convertTime(long timestamp) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date(timestamp);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(date);
    }
}
