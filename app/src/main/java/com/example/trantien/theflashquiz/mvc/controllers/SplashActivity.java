package com.example.trantien.theflashquiz.mvc.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.trantien.theflashquiz.R;

import java.io.InputStream;

/**
 * Created by Zuka on 9/18/18.
 */
public class SplashActivity extends Activity {

    protected boolean _active = true;
    protected int _splashTime = 2500;
    InputStream inputStream = null;
    public RelativeLayout main_home_layout;
    public TextView txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        txtText = findViewById(R.id.txtText);

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                    _active = true;
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        t.start();

        Thread splshThread = new Thread() {
            @Override
            public void run() {

                try {
                    int wainted = 0;
                    while (_active && (wainted < _splashTime)) {
                        sleep(100);
                        if (_active) {
                            wainted += 100;
                        }
                    }
                } catch (InterruptedException ex) {

                } finally {

                    startMainScreen();
                    inputStream = null;
                    finish();

                }
            }
        };
        splshThread.start();


    }

    public void startMainScreen() {
        Intent inst = new Intent(this, HomeActivity.class);
        inst.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(inst);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            _active = false;
        }
        return true;
    }
}