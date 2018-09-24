package com.example.trantien.theflashquiz.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Zuka on 9/18/18.
 */
public class MySharedPreferences {
    public static final String MyPREFERENCES = "MyPrefs" ;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public MySharedPreferences(Context context) {
        sharedPreferences=context.getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void Save(String key,String value){
        editor.putString(key, value);
        editor.commit();
        return;
    }
    public String Get(String key){
        return sharedPreferences.getString(key,"NULL");
    }
}
