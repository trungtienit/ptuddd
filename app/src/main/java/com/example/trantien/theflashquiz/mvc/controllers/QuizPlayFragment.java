package com.example.trantien.theflashquiz.mvc.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.trantien.theflashquiz.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QuizPlayFragment extends Fragment {
    @BindView(R.id.btnNext)
    ImageButton btnNext;
    QuizPlayListener mListener;

    String a;

    public QuizPlayFragment() {
        // Required empty public constructor
    }

    public static QuizPlayFragment newInstance() {
        return new QuizPlayFragment();
    }

    public void init(String a) {
        this.a = a;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz_play, container, false);
        if (v != null) {
            ButterKnife.bind(this, v);
        }
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoNext();
            }
        });
        Toast.makeText(container.getContext(), a, Toast.LENGTH_SHORT).show();

        return v;
    }

    public void setListener(QuizPlayListener listener){
        mListener =listener;
    }

   public interface QuizPlayListener {

       void gotoNext();
   }
}