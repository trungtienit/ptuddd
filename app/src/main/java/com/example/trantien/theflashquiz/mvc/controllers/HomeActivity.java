package com.example.trantien.theflashquiz.mvc.controllers;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trantien.theflashquiz.R;
import com.example.trantien.theflashquiz.managers.Prefs;
import com.example.trantien.theflashquiz.managers.RealmManager;
import com.example.trantien.theflashquiz.mvc.models.QuestionBank;
import com.example.trantien.theflashquiz.mvc.models.QuestionBankWrapper;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import io.realm.RealmResults;

import static com.example.trantien.theflashquiz.utils.Utils.KEY_ANONYMOUS;
import static com.example.trantien.theflashquiz.utils.Utils.KEY_EXISTING;
import static com.example.trantien.theflashquiz.utils.Utils.KEY_FACEBOOK;
import static com.example.trantien.theflashquiz.utils.Utils.KEY_NEW;
import static com.example.trantien.theflashquiz.utils.Utils.KEY_QUESTION_BANK_ID;
import static com.example.trantien.theflashquiz.utils.Utils.KEY_ROOM_ID;
import static com.example.trantien.theflashquiz.utils.Utils.KEY_TYPE_ROOM;
import static com.example.trantien.theflashquiz.utils.Utils.KEY_TYPE_USER;

/**
 * Created by Zuka on 9/18/18.
 */
public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.drawer)
    DrawerLayout drawer;

    @BindView(R.id.btn_create_room)
    Button btnCreateRoom;

    @BindView(R.id.btn_find_room)
    Button btnFindRoom;

    private ImageView imvAvatar;
    private TextView tvName;
    private TextView tvMail;
    private String key;
    private Dialog mDialog;
    ArrayList<String> mItems;

    private RealmManager mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bind(this);

        navigationView.setNavigationItemSelectedListener(this);
        imvAvatar = navigationView.getHeaderView(0).findViewById(R.id.imageView);
        tvName = navigationView.getHeaderView(0).findViewById(R.id.tv_name);
        tvMail = navigationView.getHeaderView(0).findViewById(R.id.tv_email);

        btnCreateRoom.setOnClickListener(this);
        btnFindRoom.setOnClickListener(this);

        Intent intent = getIntent();
        key = intent.getExtras().getString(KEY_TYPE_USER, KEY_ANONYMOUS);
        init();
        updateUI();
    }

    private void init() {
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//          chatAdapter = new ChatAdapter(this, mModel.getMessages());
//        mRecyclerView.setAdapter(chatAdapter);
//        mRecyclerView.scrollToPosition(mModel.getMessages().size() - 1);

        mRealm = RealmManager.getInstance();

        if (!Prefs.with(this).getPreLoad()) {
            setRealmData();
        }
        mRealm.refresh();
    }

    private void setRealmData() {
        // generate original data
        for(QuestionBank questionBank: QuestionBankWrapper.getAllQuestionBank())
          mRealm.addQuestionBank(questionBank);
        Prefs.with(this).setPreLoad(true);
    }

    public void showRoomDialog(final boolean newRoom) {
        mDialog = new Dialog(this,R.style.myDialog);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_room, null);
        Button submitRoomName = view.findViewById(R.id.button_room_submit);
        final EditText editTextRoomName = view.findViewById(R.id.edt_room_name);
        final Spinner spItems = view.findViewById(R.id.sp_folder);

        if (mItems == null) {
            mItems = new ArrayList<>();
            RealmResults<QuestionBank> data = mRealm.getQuestionBanks();
            for (int i = 0; i< data.size(); i++){
                mItems.add(data.get(i).getId());
            }
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spItems.setAdapter(spinnerAdapter);
        if (newRoom) {
            spItems.setVisibility(View.VISIBLE);
        } else {
            spItems.setVisibility(View.GONE);
        }
        submitRoomName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextRoomName.getText().toString().trim().isEmpty()) {
                    showToast("Enter a valid Name");
                } else {
                    if (newRoom)
                        gotoRoom(editTextRoomName.getText().toString(), spItems.getSelectedItem().toString());
                    else
                        gotoRoom(editTextRoomName.getText().toString(), null);
                }
            }
        });
        mDialog.setContentView(view);

        mDialog.show();
    }

    private void gotoRoom(String roomId, String questionBank) {
        mDialog.dismiss();
        mDialog = null;

        Intent intent = new Intent(this, RoomChatActivity.class);
        intent.putExtra(KEY_ROOM_ID, roomId);
        if (questionBank != null) {
            intent.putExtra(KEY_QUESTION_BANK_ID, questionBank);
            intent.putExtra(KEY_TYPE_ROOM, KEY_NEW );
        }else
            intent.putExtra(KEY_TYPE_ROOM, KEY_EXISTING);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        if (key.equals(KEY_FACEBOOK)) {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create_room:
                showRoomDialog(true);
                break;
            case R.id.btn_find_room:
                showRoomDialog(false);
                break;
            default:
                return;
        }
    }
}
