package com.example.trantien.theflashquiz.interfaces;

import com.example.trantien.theflashquiz.mvc.models.ChatPojo;

import java.util.ArrayList;

/**
 * Created by Zuka on 9/19/18.
 */
public interface ModelCallBacks {
    void onModelUpdated(ArrayList<ChatPojo> messages);
}