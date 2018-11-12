package com.example.trantien.theflashquiz.mvc.models;

import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;

/**
 * Created by Zuka on 9/18/18.
 */
public class ChatModel {
    private String msgKey;
    private long timeStamp;
    private String message;
    private String senderId;

    public ChatModel(DataSnapshot dataSnapshot){
        HashMap<String, Object> object = (HashMap<String, Object>) dataSnapshot.getValue();
        this.msgKey=dataSnapshot.getKey();
        this.message=object.get("content").toString();
        this.senderId=object.get("senderId").toString();
        this.timeStamp= Long.parseLong(object.get("time").toString());
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderId() {
        return senderId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

}
