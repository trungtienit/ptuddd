package com.example.trantien.theflashquiz.mvc.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.trantien.theflashquiz.R;
import com.example.trantien.theflashquiz.managers.Prefs;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;

public class ProFileActivity extends DrawerActivity {

    @BindView(R.id.tv_name)
    TextView textViewName;

    @BindView(R.id.btn_ok)
    TextView btn_ok;

    @BindView(R.id.text_best_score)
    TextView textViewBestCore;

    @BindView(R.id.text_best_combo)
    TextView textViewBestCombo;

    @BindView(R.id.text_count)
    TextView textViewBestTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        addContentView(R.layout.activity_profile);
        super.onCreate(savedInstanceState);

        textViewName.setText(Prefs.with(this).getName());
        textViewBestCore.setText(Prefs.with(this).getScore().toString());
        textViewBestTime.setText(Prefs.with(this).getCount().toString());
        textViewName.setText(Prefs.with(this).getName());
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
