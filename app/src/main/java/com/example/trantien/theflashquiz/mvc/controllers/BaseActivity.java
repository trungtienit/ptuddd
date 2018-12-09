package com.example.trantien.theflashquiz.mvc.controllers;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.trantien.theflashquiz.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Zuka on 9/18/18.
 */
public class BaseActivity extends AppCompatActivity {

    protected Unbinder mBinder;
    public ProgressDialog mProgressDialog;
    BackListener backListener;
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    protected void bind(Activity activity){
        mBinder= ButterKnife.bind(activity);
    }
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void hideKeyboard(View view) {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken() , 0);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }

    @Override
    protected void onDestroy() {
        if (mBinder != null)
            mBinder.unbind();
        super.onDestroy();
    }


    protected void showToast(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    public void slideFromRight(View view){
        TranslateAnimation animation = new TranslateAnimation(1500.0f, 0.0f, 0.0f, 0.0f); // new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(1500); // animation duration
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    public void slideToRight(View view){
        TranslateAnimation animation = new TranslateAnimation(0.0f, 1500.0f, 0.0f, 0.0f); // new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(1500); // animation duration
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }
    public void slideToLeft(View view){
        TranslateAnimation animation = new TranslateAnimation(0.0f, -1500.0f, 0.0f, 0.0f); // new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(1500); // animation duration
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    public void slideFromLeft(View view){
        TranslateAnimation animation = new TranslateAnimation(-1500.0f, 0.0f, 0.0f, 0.0f); // new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(1500); // animation duration
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }


    protected void showBackConfirmDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                backListener.onYesClicked();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
    public void setBackListener(BackListener backListener) {
        this.backListener = backListener;
    }

    public interface BackListener {

        void onYesClicked();
    }
}
