package com.example.trantien.theflashquiz.interfaces;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by Zuka on 9/19/18.
 */
public interface FirebaseCallBacks {
    void onNewMessage(DataSnapshot dataSnapshot);
}

