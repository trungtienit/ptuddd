package com.example.trantien.theflashquiz.mvc.models;

import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;

import static com.example.trantien.theflashquiz.utils.Utils.KEY_RETURN_SCORE;
import static com.example.trantien.theflashquiz.utils.Utils.QUESTION_BANK;

/**
 * Created by Zuka on 9/18/18.
 */
public class ChatModel {
    public enum MessageType
    {
        NORMAL,
        NOTICE_FOLDER,
        NOTICE_SCORE,
    }

    private String msgKey;
    private long timeStamp;
    private String message;
    private String senderId;
    private MessageType type;

    public ChatModel(DataSnapshot dataSnapshot){
        HashMap<String, Object> object = (HashMap<String, Object>) dataSnapshot.getValue();
        this.msgKey=dataSnapshot.getKey();
        this.message=object.get("content").toString();

        if(this.message.startsWith(QUESTION_BANK)){
            this.type= MessageType.NOTICE_FOLDER;
        }else if(this.message.startsWith(KEY_RETURN_SCORE)){
            this.type= MessageType.NOTICE_SCORE;
        }else{
            this.type=MessageType.NORMAL;
        }

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

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
