package com.example.trantien.theflashquiz.mvc.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.trantien.theflashquiz.R;

import butterknife.BindView;

public class ProFileActivity extends Activity {

    @BindView(R.id.text_name)
    TextView textViewName;

    @BindView(R.id.text_email)
    TextView textViewEmail;

    @BindView(R.id.text_phone)
    TextView textViewPhone;

    @BindView(R.id.text_google)
    TextView textViewGoogle;

    @BindView(R.id.text_facebook)
    TextView textViewFace;

    @BindView(R.id.text_best_score)
    TextView textViewBestCore;

    @BindView(R.id.text_best_combo)
    TextView textViewBestCombo;

    @BindView(R.id.text_best_time)
    TextView textViewBestTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
}
