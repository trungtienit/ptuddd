package com.example.trantien.theflashquiz.mvc.controllers;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.trantien.theflashquiz.R;
import com.example.trantien.theflashquiz.interfaces.FirebaseCallBacks;
import com.example.trantien.theflashquiz.interfaces.MessageCallBacks;
import com.example.trantien.theflashquiz.managers.FirebaseManager;
import com.example.trantien.theflashquiz.mvc.models.MessageModel;
import com.example.trantien.theflashquiz.mvc.views.ChatAdapter;
import com.example.trantien.theflashquiz.utils.Utils;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;

import butterknife.BindView;

import static com.example.trantien.theflashquiz.utils.Utils.KEY_NEW;
import static com.example.trantien.theflashquiz.utils.Utils.KEY_QUESTION_BANK_ID;
import static com.example.trantien.theflashquiz.utils.Utils.KEY_RETURN_SCORE;
import static com.example.trantien.theflashquiz.utils.Utils.KEY_ROOM_ID;
import static com.example.trantien.theflashquiz.utils.Utils.KEY_TYPE_ROOM;
import static com.example.trantien.theflashquiz.utils.Utils.QUESTION_BANK;
import static com.example.trantien.theflashquiz.utils.Utils.REQUEST_SCORE;
import static com.example.trantien.theflashquiz.utils.Utils.createNewRoomWith;

/**
 * Created by Zuka on 9/18/18.
 */
public class RoomChatActivity extends DrawerActivity implements MessageCallBacks, FirebaseCallBacks, View.OnClickListener, BaseActivity.BackListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.btn_start)
    Button btnStart;

    @BindView(R.id.btn_send)
    ImageButton btnSend;

    @BindView(R.id.edt_message)
    EditText edtMessage;

    @BindView(R.id.llMessage)
    LinearLayout llMessage;

    @BindView(R.id.myFoat)
    FloatingActionMenu myFoat;


    private String mRoomName;
    private String mKeyId;
    private MessageModel mModel;

    private ChatAdapter chatAdapter;
    private String currentID = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        addContentView(R.layout.activity_room_chat);
        super.onCreate(savedInstanceState);
        showLoading();
        bind(this);
        String key = getIntent().getExtras().getString(KEY_TYPE_ROOM);
        mRoomName = getIntent().getExtras().getString(KEY_ROOM_ID);
        mKeyId = getIntent().getExtras().getString(KEY_QUESTION_BANK_ID);
        setTitle("tQuiz");
        setListener(mRoomName);
        if (key.equals(KEY_NEW)) {
            //send question bank id
            sendMessageToFirebase(createNewRoomWith(mKeyId));
        }
        mModel = new MessageModel(this);

        this.init();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        hideLoading();
                        sendMessageToFirebase("Hi");
                    }
                }, 5000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
                intent.putExtra(KEY_QUESTION_BANK_ID, currentID);
                startActivityForResult(intent, REQUEST_SCORE);
                break;
            case R.id.btn_send:
                sendMessageToFirebase(edtMessage.getText().toString());
                edtMessage.setText("");
                View view = this.getCurrentFocus();
                if (view != null) {
                    hideKeyboard(view);
                }

                break;
        }
    }

    private void init() {
        btnStart.setEnabled(false);
        btnStart.setOnClickListener(this);
        edtMessage.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(this, mModel.getMessages());
        mRecyclerView.setAdapter(chatAdapter);
        mRecyclerView.scrollToPosition(mModel.getMessages().size() - 1);

    }

    @Override
    public void onBackPressed() {
        setBackListener(this);
        showBackConfirmDialog();
    }

    @Override
    public void onNewMessage(DataSnapshot dataSnapshot) {
        HashMap<String, Object> object = (HashMap<String, Object>) dataSnapshot.getValue();
        if (currentID == null) {
            if (object.get("content").toString().startsWith(QUESTION_BANK)) {
                currentID = object.get("content").toString().substring(QUESTION_BANK.length());
                btnStart.setEnabled(true);
            }
        }

        mModel.addMessages(dataSnapshot);
    }

    @Override
    public void onModelUpdated() {
        chatAdapter.notifyDataSetChanged();
        mRecyclerView.smoothScrollToPosition(chatAdapter.getItemCount() - 1);

    }

    public void sendMessageToFirebase(String message) {
        if (!message.trim().equals("")) {
            FirebaseManager.getInstance(mRoomName, this).sendMessageToFirebase(message);
        }
    }

    public void setListener(String roomName) {
        FirebaseManager.getInstance(roomName, this).addMessageListeners();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseManager.getInstance(mRoomName, this).removeListeners();
        FirebaseManager.getInstance(mRoomName, this).destroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQUEST_SCORE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                sendMessageToFirebase(Utils.sendMyResult(data.getStringExtra(KEY_RETURN_SCORE)));
            }
        }
    }

    public void openChat(View view) {
        llMessage.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.GONE);
        myFoat.toggle(true);
    }

    public void openPlay(View view) {
        llMessage.setVisibility(View.GONE);
        btnStart.setVisibility(View.VISIBLE);
        myFoat.toggle(true);
    }

    @Override
    public void onYesClicked() {
        finish();
        super.onBackPressed();
    }
}
