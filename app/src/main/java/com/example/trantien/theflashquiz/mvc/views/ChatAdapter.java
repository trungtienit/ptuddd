package com.example.trantien.theflashquiz.mvc.views;

/**
 * Created by Zuka on 9/18/18.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.trantien.theflashquiz.R;
import com.example.trantien.theflashquiz.mvc.models.ChatModel;
import com.example.trantien.theflashquiz.mvc.models.QuizBank;
import com.example.trantien.theflashquiz.mvc.models.QuizBankWrapper;
import com.example.trantien.theflashquiz.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.FollowerViewHolder> {


    private ArrayList<ChatModel> chatList;
    private Context context;

    public ChatAdapter(Context context, ArrayList<ChatModel> messages) {
        this.context=context;
        this.chatList=messages;
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public FollowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.row_chat_adapter, parent, false);
        return new FollowerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FollowerViewHolder holder, final int position) {

            if (chatList.get(position).getSenderId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {

                holder.layoutLeftMessages.setVisibility(View.GONE);
                holder.layoutRightMessages.setVisibility(View.VISIBLE);

                holder.messagesTextRight.setText(chatList.get(position).getMessage());
                holder.timeMessagesRight.setText(Utils.convertTime(chatList.get(position).getTimeStamp()));

            } else {

                holder.layoutLeftMessages.setVisibility(View.VISIBLE);
                holder.layoutRightMessages.setVisibility(View.GONE);

                holder.messagesTextLeft.setText(chatList.get(position).getMessage());
                holder.timeMessagesLeft.setText(Utils.convertTime(chatList.get(position).getTimeStamp()));
            }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    class FollowerViewHolder extends RecyclerView.ViewHolder {

        private TextView messagesTextLeft, timeMessagesLeft, messagesTextRight,timeMessagesRight;
        private LinearLayout layoutLeftMessages, layoutRightMessages;

        FollowerViewHolder(View convertView) {
            super(convertView);

            messagesTextLeft = (TextView) convertView.findViewById(R.id.text_message_left);
            timeMessagesLeft =(TextView) convertView.findViewById(R.id.text_time_messages_left);
            messagesTextRight =(TextView) convertView.findViewById(R.id.text_message_right);
            timeMessagesRight=(TextView) convertView.findViewById(R.id.text_time_message_right);

            layoutLeftMessages =(LinearLayout) convertView.findViewById(R.id.layout_message_left);
            layoutRightMessages =(LinearLayout) convertView.findViewById(R.id.layout_message_right);
        }
    }
}

class ViewPickTopic extends RecyclerView.ViewHolder{
    public TextView textViewPickTopic,textViewTime;
    public ImageView imageViewPickTopic;

    public ViewPickTopic(View itemView) {
        super(itemView);
        textViewPickTopic = (TextView) itemView.findViewById(R.id.text_pick_topic);
        textViewTime = (TextView) itemView.findViewById(R.id.text_time_pick_topic);
        imageViewPickTopic = (ImageView) itemView.findViewById(R.id.image_pick_topic);
    }
}

class ViewReturnResult extends RecyclerView.ViewHolder{
    public TextView textViewCorrect, textViewUnCorrect,textViewTime;
    public ImageView imageViewResult;

    public ViewReturnResult(View itemView) {
        super(itemView);
        textViewCorrect = (TextView)itemView.findViewById(R.id.result_correct);
        textViewUnCorrect = (TextView)itemView.findViewById(R.id.result_uncorrect);
        imageViewResult = (ImageView)itemView.findViewById(R.id.image_return_result);
        textViewTime = (TextView)itemView.findViewById(R.id.text_time_return_result);
    }
}