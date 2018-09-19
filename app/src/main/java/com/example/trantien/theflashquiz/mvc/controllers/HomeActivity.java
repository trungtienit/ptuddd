package com.example.trantien.theflashquiz.mvc.controllers;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.trantien.theflashquiz.R;
import com.example.trantien.theflashquiz.interfaces.FirebaseCallBacks;
import com.example.trantien.theflashquiz.interfaces.ModelCallBacks;
import com.example.trantien.theflashquiz.managers.FirebaseManager;
import com.example.trantien.theflashquiz.mvc.models.ChatPojo;
import com.example.trantien.theflashquiz.mvc.models.MessageModel;
import com.example.trantien.theflashquiz.mvc.views.ChatAdapter;
import com.example.trantien.theflashquiz.utils.Utils;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

/**
 * Created by Zuka on 9/18/18.
 */
public class HomeActivity extends BaseActivity implements ModelCallBacks,View.OnClickListener, FirebaseCallBacks {

    private EditText mEdittextChat;
    private RecyclerView mRecyclerView;
    private String mRoomName;
    private MessageModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRoomName=Utils.EXTRA_ROOM_NAME;
        mModel=new MessageModel(this);
        setListener(mRoomName);

        mEdittextChat=(EditText) findViewById(R.id.edittext_chat_message);
        mRecyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.button_send_message).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_send_message:
                sendMessageToFirebase(mRoomName,mEdittextChat.getText().toString());
                hideKeyboard(v);
                break;
        }
    }

    public void updateList(ArrayList<ChatPojo> list) {
        ChatAdapter chatAdapter=new ChatAdapter(this,list);
        mRecyclerView.setAdapter(chatAdapter);
        mRecyclerView.scrollToPosition(list.size()-1);
    }

    @Override
    public void onNewMessage(DataSnapshot dataSnapshot) {
        mModel.addMessages(dataSnapshot);
    }

    @Override
    public void onModelUpdated(ArrayList<ChatPojo> messages) {
        if (messages.size()>0) {
            updateList(messages);
        }
    }

    public void sendMessageToFirebase(String roomName, String message) {
        if (!message.trim().equals("")){
            FirebaseManager.getInstance(roomName,this).sendMessageToFirebase(message);
        }
        mEdittextChat.setText("");
    }

    public void setListener(String roomName) {
        FirebaseManager.getInstance(roomName,this).addMessageListeners();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseManager.getInstance(mRoomName,this).removeListeners();
        FirebaseManager.getInstance(mRoomName,this).destroy();
        Log.i("MYLOG","detroy");
    }

}
