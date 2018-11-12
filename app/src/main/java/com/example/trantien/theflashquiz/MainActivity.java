package com.example.trantien.theflashquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.trantien.theflashquiz.mvc.controllers.LoginActivity;
import com.example.trantien.theflashquiz.mvc.controllers.PlayerActivity;
import com.example.trantien.theflashquiz.mvc.controllers.ProFileActivity;
import com.example.trantien.theflashquiz.mvc.controllers.QuizPlayFragment;
import com.example.trantien.theflashquiz.mvc.controllers.RoomChatActivity;
import com.example.trantien.theflashquiz.mvc.controllers.SplashActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_quizbank_result);
        Intent intent = new Intent(this, ProFileActivity.class);
        startActivity(intent);
        finish();
    }

}
