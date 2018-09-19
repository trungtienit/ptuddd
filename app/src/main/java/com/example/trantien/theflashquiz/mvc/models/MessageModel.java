package com.example.trantien.theflashquiz.mvc.models;

import com.example.trantien.theflashquiz.interfaces.ModelCallBacks;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

/**
 * Created by Zuka on 9/19/18.
 */
public class MessageModel {
    private ArrayList<ChatPojo> mMessages;
    ModelCallBacks mModelCallBacks;

    public MessageModel(ModelCallBacks modelCallBacks){
        this.mModelCallBacks=modelCallBacks;
    }

    public void addMessages(DataSnapshot dataSnapshot){
        if (mMessages==null){
            mMessages= new ArrayList<>();
        }
        ChatPojo chatPojo=new ChatPojo(dataSnapshot);
        mMessages.add(chatPojo);
        mModelCallBacks.onModelUpdated(mMessages);
    }
}
