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
import java.util.List;

import static com.example.trantien.theflashquiz.mvc.models.ChatModel.MessageType.NOTICE_FOLDER;
import static com.example.trantien.theflashquiz.mvc.models.ChatModel.MessageType.NOTICE_SCORE;


public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<ChatModel> chatList;
    private Context context;

    public ChatAdapter(Context context, ArrayList<ChatModel> messages) {
        this.context = context;
        this.chatList = messages;
    }

    @Override
    public int getItemViewType(int position) {

        if (chatList.get(position).getType() == NOTICE_FOLDER)
            return 1;
        if (chatList.get(position).getType() == NOTICE_SCORE)
            return 2;
        return 0;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case 0:
                itemView = LayoutInflater.from(context).inflate(R.layout.row_chat_adapter, parent, false);
                return new NormalViewHolder(itemView);
            case 1:
                itemView = LayoutInflater.from(context).inflate(R.layout.row_quizbank_result, parent, false);
                return new BankViewHolder(itemView);
            case 2:
                itemView = LayoutInflater.from(context).inflate(R.layout.row_result, parent, false);
                return new ScoreViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ChatModel object = chatList.get(position);
        if (object != null) {
            switch (getItemViewType(position)) {
                case 0:
                    if (chatList.get(position).getSenderId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {

                        ((NormalViewHolder) holder).layoutLeftMessages.setVisibility(View.GONE);
                        ((NormalViewHolder) holder).layoutRightMessages.setVisibility(View.VISIBLE);

                        ((NormalViewHolder) holder).messagesTextRight.setText(chatList.get(position).getMessage());
                        ((NormalViewHolder) holder).timeMessagesRight.setText(Utils.convertTime(chatList.get(position).getTimeStamp()));

                    } else {
                        ((NormalViewHolder) holder).layoutLeftMessages.setVisibility(View.VISIBLE);
                        ((NormalViewHolder) holder).layoutRightMessages.setVisibility(View.GONE);

                        ((NormalViewHolder) holder).messagesTextLeft.setText(chatList.get(position).getMessage());
                        ((NormalViewHolder) holder).timeMessagesLeft.setText(Utils.convertTime(chatList.get(position).getTimeStamp()));
                    }

                    break;
                case 1:
                    if (chatList.get(position).getSenderId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {

                        ((BankViewHolder) holder).layoutLeftMessages.setVisibility(View.GONE);
                        ((BankViewHolder) holder).layoutRightMessages.setVisibility(View.VISIBLE);

                        ((BankViewHolder) holder).txtRight.setText(object.getMessage().substring(Utils.QUESTION_BANK.length()));

                        ((BankViewHolder) holder).timeMessagesRight.setText(Utils.convertTime(chatList.get(position).getTimeStamp()));

                    } else {
                        ((BankViewHolder) holder).layoutLeftMessages.setVisibility(View.VISIBLE);
                        ((BankViewHolder) holder).layoutRightMessages.setVisibility(View.GONE);

                        ((BankViewHolder) holder).txtLeft.setText(object.getMessage().substring(Utils.QUESTION_BANK.length()));

                        ((BankViewHolder) holder).timeMessagesLeft.setText(Utils.convertTime(chatList.get(position).getTimeStamp()));
                    }

                    break;
                case 2:
                    String[] strs = object.getMessage().substring(Utils.KEY_RETURN_SCORE.length()).split("#");

                    if (chatList.get(position).getSenderId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {

                        ((ScoreViewHolder) holder).layoutLeftMessages.setVisibility(View.GONE);
                        ((ScoreViewHolder) holder).layoutRightMessages.setVisibility(View.VISIBLE);

                        ((ScoreViewHolder) holder).result_correct_right.setText(strs[0]);
                        ((ScoreViewHolder) holder).result_uncorrect_right.setText(strs[1]);
                        ((ScoreViewHolder) holder).timeMessagesRight.setText(Utils.convertTime(chatList.get(position).getTimeStamp()));

                    } else {
                        ((ScoreViewHolder) holder).layoutLeftMessages.setVisibility(View.VISIBLE);
                        ((ScoreViewHolder) holder).layoutRightMessages.setVisibility(View.GONE);
                        ((ScoreViewHolder) holder).result_correct_left.setText(strs[0]);
                        ((ScoreViewHolder) holder).result_uncorrect_left.setText(strs[1]);

                        ((ScoreViewHolder) holder).timeMessagesLeft.setText(Utils.convertTime(chatList.get(position).getTimeStamp()));
                    }

                    break;

            }
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {

        private TextView messagesTextLeft, timeMessagesLeft, messagesTextRight, timeMessagesRight;
        private LinearLayout layoutLeftMessages, layoutRightMessages;

        NormalViewHolder(View convertView) {
            super(convertView);

            messagesTextLeft = convertView.findViewById(R.id.text_message_left);
            messagesTextRight = convertView.findViewById(R.id.text_message_right);
            timeMessagesLeft = convertView.findViewById(R.id.text_time_messages_left);
            timeMessagesRight = convertView.findViewById(R.id.text_time_message_right);

            layoutLeftMessages = convertView.findViewById(R.id.layout_message_left);
            layoutRightMessages = convertView.findViewById(R.id.layout_message_right);
        }
    }

    class ScoreViewHolder extends RecyclerView.ViewHolder {

        private TextView result_correct_left, result_uncorrect_left,result_correct_right,result_uncorrect_right,timeMessagesLeft, timeMessagesRight;
        private LinearLayout layoutLeftMessages, layoutRightMessages;

        ScoreViewHolder(View convertView) {
            super(convertView);
            result_correct_left = convertView.findViewById(R.id.result_correct);
            result_uncorrect_left = convertView.findViewById(R.id.result_uncorrect);

            result_correct_right = convertView.findViewById(R.id.result_correct_right);
            result_uncorrect_right = convertView.findViewById(R.id.result_uncorrect_right);

            layoutLeftMessages = convertView.findViewById(R.id.layout_message_left);
            layoutRightMessages = convertView.findViewById(R.id.layout_message_right);
            timeMessagesLeft = convertView.findViewById(R.id.text_time_messages_left);
            timeMessagesRight = convertView.findViewById(R.id.text_time_message_right);
        }
    }

    class BankViewHolder extends RecyclerView.ViewHolder {

        private TextView txtLeft,txtRight,timeMessagesLeft, timeMessagesRight;
        private LinearLayout layoutLeftMessages, layoutRightMessages;
        BankViewHolder(View convertView) {
            super(convertView);
            txtLeft = convertView.findViewById(R.id.text_message_left);
            txtRight = convertView.findViewById(R.id.text_message_right);
            timeMessagesLeft = convertView.findViewById(R.id.text_time_messages_left);
            timeMessagesRight = convertView.findViewById(R.id.text_time_message_right);
            layoutLeftMessages = convertView.findViewById(R.id.layout_message_left);
            layoutRightMessages = convertView.findViewById(R.id.layout_message_right);
        }
    }
}
