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

    private String topic;
    private int picturePickTopic;
    private int numCorrect;
    private int numUnCorrect;
    private int pictureResult;

    public ChatModel(String msgKey, String topic, int picturePickTopic, int numCorrect, int numUnCorrect, int pictureResult) {
        this.msgKey = msgKey;
        this.topic = topic;
        this.picturePickTopic = picturePickTopic;
        this.numCorrect = numCorrect;
        this.numUnCorrect = numUnCorrect;
        this.pictureResult = pictureResult;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getPicturePickTopic() {
        return picturePickTopic;
    }

    public void setPicturePickTopic(int picturePickTopic) {
        this.picturePickTopic = picturePickTopic;
    }

    public int getNumCorrect() {
        return numCorrect;
    }

    public void setNumCorrect(int numCorrect) {
        this.numCorrect = numCorrect;
    }

    public int getNumUnCorrect() {
        return numUnCorrect;
    }

    public void setNumUnCorrect(int numUnCorrect) {
        this.numUnCorrect = numUnCorrect;
    }

    public int getPictureResult() {
        return pictureResult;
    }

    public void setPictureResult(int pictureResult) {
        this.pictureResult = pictureResult;
    }

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
