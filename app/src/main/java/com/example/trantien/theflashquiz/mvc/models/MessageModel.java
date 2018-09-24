package com.example.trantien.theflashquiz.mvc.models;

import com.example.trantien.theflashquiz.interfaces.MessageCallBacks;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

/**
 * Created by Zuka on 9/19/18.
 */
public class MessageModel {
    private ArrayList<ChatModel> mMessages;
    MessageCallBacks mMessageCallBacks;

    public MessageModel(MessageCallBacks messageCallBacks){
        this.mMessageCallBacks = messageCallBacks;
        if(mMessages==null)
            mMessages= new ArrayList<>();
    }

    public void addMessages(DataSnapshot dataSnapshot){
        if (mMessages==null){
            mMessages= new ArrayList<>();
        }
        mMessages.add(new ChatModel(dataSnapshot));
        mMessageCallBacks.onModelUpdated();
    }

    public ArrayList<ChatModel> getMessages() {
        return mMessages;
    }

}
