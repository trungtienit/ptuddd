package com.example.trantien.theflashquiz.managers;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import de.hdodenhof.circleimageview.CircleImageView;

public class Prefs {

    private static final String PRE_LOAD = "preLoad";
    private static final String PREFS_NAME = "prefs";
    private static final String FULL_NAME = "FULLNAME";
    private static final String EMAIL = "EMAIL";
    private static final String URL_ICON = "URL_ICON";
    private static final String SCORE = "SOCRE";
    private static final String COUNT = "COUNT";
    private static Prefs instance;
    private final SharedPreferences sharedPreferences;

    public Prefs(Context context) {

        sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static Prefs with(Context context) {

        if (instance == null) {
            instance = new Prefs(context);
        }
        return instance;
    }

    public void setPreLoad(boolean totalTime) {

        sharedPreferences
                .edit()
                .putBoolean(PRE_LOAD, totalTime)
                .apply();
    }

    public void setName(String name) {

        sharedPreferences
                .edit()
                .putString(FULL_NAME, name)
                .apply();
    }

    public String getName() {

        return sharedPreferences.getString(FULL_NAME, "Anonymous");
    }

    public void setEmail(String mail) {

        sharedPreferences
                .edit()
                .putString(EMAIL, mail)
                .apply();
    }

    public String getEmail() {

        return sharedPreferences.getString(EMAIL, "example@email.com");
    }

    public void setURL(String url) {

        sharedPreferences
                .edit()
                .putString(URL_ICON, url)
                .apply();
    }

    public String getUrl() {

        return sharedPreferences.getString(URL_ICON, null);
    }

    public boolean getPreLoad() {
        return sharedPreferences.getBoolean(PRE_LOAD, false);
    }

    public void logOut() {
        try {
            sharedPreferences
                    .edit().remove(PREFS_NAME)
                    .remove(FULL_NAME)
                    .remove(EMAIL)
                    .remove(URL_ICON)
                    .commit();
            Log.i("LOGOUT", "Success");
        } catch (Exception e) {
            Log.i("LOGOUT", "Fail");
        }
    }

    public void updateScore(Integer score) {
        int a = getScore();
        if (score > a)
            sharedPreferences
                    .edit()
                    .putInt(SCORE, score)
                    .apply();
    }

    public Integer getScore() {

        return sharedPreferences.getInt(SCORE, 0);
    }

    public void updateCount() {
        int a = getCount();
        sharedPreferences
                .edit()
                .putInt(COUNT, a++)
                .apply();
    }

    public Integer getCount() {
        return sharedPreferences.getInt(COUNT, 0);
    }

}
