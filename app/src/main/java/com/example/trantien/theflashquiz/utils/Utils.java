package com.example.trantien.theflashquiz.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Zuka on 9/18/18.
 */
public class Utils {
    public static final int REQUEST_SCORE = 0;

    public static final String KEY_FACEBOOK ="FACEBOOK";
    public static final String KEY_ANONYMOUS="ANONYMOUS";
    public static final String KEY_TYPE_USER="TYPE_USER";
    public static final String KEY_ROOM_ID="ROOM_ID";
    public static final String KEY_QUESTION_BANK_ID ="NEW_ROOM";
    public static final String QUESTION_BANK ="Question Bank :";
    public static final String KEY_TYPE_ROOM ="TYPE_ROOM";
    public static final String KEY_EXISTING ="EXISTING";

    public static final String KEY_NEW ="NEW";
    public static final String KEY_RETURN_SCORE ="RETURN_SCORE";

    public static int COUNT_FOLDER=0;
    public static int COUNT_QUESTION=0;

    public static String convertTime(long timestamp) {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date(timestamp);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(date);
    }

    public static String createNewRoomWith(String mFolder) {
        return QUESTION_BANK + mFolder;
    }

    public static String sendMyResult(String result) {
        return KEY_RETURN_SCORE +result;
    }
}
