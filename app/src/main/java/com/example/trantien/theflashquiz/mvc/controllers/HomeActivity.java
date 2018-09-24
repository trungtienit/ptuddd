package com.example.trantien.theflashquiz.mvc.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trantien.theflashquiz.R;
import com.example.trantien.theflashquiz.interfaces.FirebaseCallBacks;
import com.example.trantien.theflashquiz.interfaces.MessageCallBacks;
import com.example.trantien.theflashquiz.managers.FirebaseManager;
import com.example.trantien.theflashquiz.mvc.models.MessageModel;
import com.example.trantien.theflashquiz.mvc.views.ChatAdapter;
import com.example.trantien.theflashquiz.utils.Utils;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.firebase.database.DataSnapshot;

import org.json.JSONObject;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.trantien.theflashquiz.utils.Utils.KEY_ANONYMOUS;
import static com.example.trantien.theflashquiz.utils.Utils.KEY_FIREBASE;

/**
 * Created by Zuka on 9/18/18.
 */
public class HomeActivity extends BaseActivity implements MessageCallBacks, FirebaseCallBacks, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.edittext_chat_message)
    EditText mEditTextChat;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.drawer)
    DrawerLayout drawer;

    private String mRoomName;

    private MessageModel mModel;
    private ChatAdapter chatAdapter;

    private ImageView imvAvatar;
    private TextView tvName;
    private TextView tvMail;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mBinder = ButterKnife.bind(this);

        mRoomName = Utils.EXTRA_ROOM_NAME;
        mModel = new MessageModel(this);

        setListener(mRoomName);

        navigationView.setNavigationItemSelectedListener(this);
        imvAvatar = navigationView.getHeaderView(0).findViewById(R.id.imageView);
        tvName = navigationView.getHeaderView(0).findViewById(R.id.tv_name);
        tvMail = navigationView.getHeaderView(0).findViewById(R.id.tv_email);

        Intent intent = getIntent();
        key = intent.getExtras().getString(KEY_ANONYMOUS, KEY_ANONYMOUS);

        init();
        updateUI();
    }

    private void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        chatAdapter = new ChatAdapter(this, mModel.getMessages());
        mRecyclerView.setAdapter(chatAdapter);
        mRecyclerView.scrollToPosition(mModel.getMessages().size() - 1);
    }

    @Override
    public void onNewMessage(DataSnapshot dataSnapshot) {
        mModel.addMessages(dataSnapshot);
    }

    @Override
    public void onModelUpdated() {
        chatAdapter.notifyDataSetChanged();
    }

    public void sendMessageToFirebase(String roomName, String message) {
        if (!message.trim().equals("")) {
            FirebaseManager.getInstance(roomName, this).sendMessageToFirebase(message);
        }
        mEditTextChat.setText("");
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

    public void sendMessage(View view) {
        sendMessageToFirebase(mRoomName, mEditTextChat.getText().toString());
        hideKeyboard(view);
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void updateUI() {
        if (key.equals(KEY_FIREBASE)) {
            showProgressDialog();
            GraphRequest request = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object,
                                                GraphResponse response) {
                            // Application code
                            String name = object.optString(getString(R.string.name));
                            String id = object.optString(getString(R.string.id));
                            String email = object.optString(getString(R.string.email));
                            URL imageURL = extractFacebookIcon(id);

                            updateInfor(imageURL.toString(), name, email);

                            hideProgressDialog();
                        }

                    });
            Bundle parameters = new Bundle();
            parameters.putString(getString(R.string.fields), getString(R.string.fields_name));
            request.setParameters(parameters);
            request.executeAsync();
        } else {
            updateUIAnonymous();
        }
    }

    private void updateUIAnonymous() {

    }

    private void updateInfor(String imageURL, String name, String email) {
        Glide.with(this)
                .load(imageURL)
                .into(imvAvatar);
        tvName.setText(name);
        tvMail.setText(email);
    }

    public URL extractFacebookIcon(String id) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL imageURL = new URL("http://graph.facebook.com/" + id
                    + "/picture?type=large");
            return imageURL;
        } catch (Throwable e) {
            return null;
        }
    }
}
