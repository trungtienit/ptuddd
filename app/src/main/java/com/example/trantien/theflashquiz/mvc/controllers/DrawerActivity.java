package com.example.trantien.theflashquiz.mvc.controllers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.trantien.theflashquiz.R;
import com.example.trantien.theflashquiz.managers.Prefs;
import com.example.trantien.theflashquiz.utils.Utils;

import butterknife.BindView;

/**
 * Created by Zuka on 9/18/18.
 */
public class DrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer)
    DrawerLayout drawer;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.text_screen_title)
    TextView mTextViewScreenTitle;

    @BindView(R.id.image_back_button)
    ImageButton mImageButtonBack;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @BindView(R.id.topBar)
    ViewGroup topBar;
    private ImageView imvAvatar;
    private TextView tvName;
    private TextView tvMail;

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = DrawerActivity.this;
        bind(this);
        mImageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLeft();
            }
        });
        imvAvatar = navigationView.getHeaderView(0).findViewById(R.id.imageView);
        tvName = navigationView.getHeaderView(0).findViewById(R.id.tv_name);
        tvMail = navigationView.getHeaderView(0).findViewById(R.id.tv_email);

        navigationView.setNavigationItemSelectedListener(this);
        imvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ProFileActivity.class);
                startActivity(intent);
            }
        });

        updateInfor(Prefs.with(this).getUrl(), Prefs.with(this).getName(), Prefs.with(this).getEmail());
    }

    protected void btnLeft() {
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawer(Gravity.START);
        } else {
            drawer.openDrawer(Gravity.START);
        }
    }

    public void addContentView(int layoutResID) {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer, null);
        FrameLayout activityContainer = fullView.findViewById(R.id.layout_container);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);
    }

    protected void setTitle(String title) {
        mTextViewScreenTitle.setText(title);
    }

    void updateInfor(String imageURL, String name, String email) {
        if (imageURL != null)
            Glide.with(this)
                    .load(imageURL)
                    .into(imvAvatar);
        else Glide.with(this)
                .load(getDrawable(R.drawable.avatar))
                .into(imvAvatar);

        tvName.setText(name);
        tvMail.setText(email);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            logOut();
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            intent.putExtra(Utils.KEY_LOGIN, Utils.KEY_SIGNOUT);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else {
            showToast("Coming soon! ...");
        }

        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    private void logOut() {
        Prefs.with(this).logOut();

    }


    protected void hideTopBar() {
        topBar.setVisibility(View.GONE);
    }

    protected void hideLoading() {
        if (progressbar != null)
            progressbar.setVisibility(View.GONE);
    }

    protected void showLoading() {
        if (progressbar != null)
            progressbar.setVisibility(View.VISIBLE);
    }

}

