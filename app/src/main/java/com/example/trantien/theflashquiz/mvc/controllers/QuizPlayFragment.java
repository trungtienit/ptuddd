package com.example.trantien.theflashquiz.mvc.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.trantien.theflashquiz.R;
import com.example.trantien.theflashquiz.mvc.models.PojoModel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QuizPlayFragment extends Fragment{
    protected boolean _active = false;
    protected boolean isPressed = false;

    //region bind data
    @BindView(R.id.btnNext)
    ImageButton btnNext;

    @BindView(R.id.txtScore)
    TextView tvScore;

    @BindView(R.id.txtFalseQuestion)
    TextView tvFalseQuestion;

    @BindView(R.id.txtTrueQuestion)
    TextView tvTrueQuestion;

    @BindView(R.id.tvQuiz)
    TextView tvQuiz;

    @BindView(R.id.tvNumber)
    TextView tvNumber;

    @BindView(R.id.btnOpt1)
    ViewGroup btnOpt1;

    @BindView(R.id.btnOpt2)
    ViewGroup btnOpt2;

    @BindView(R.id.btnOpt3)
    ViewGroup btnOpt3;

    @BindView(R.id.btnOpt4)
    ViewGroup btnOpt4;

    @BindView(R.id.tvOpt1)
    TextView tvOpt1;

    @BindView(R.id.tvOpt2)
    TextView tvOpt2;

    @BindView(R.id.tvOpt3)
    TextView tvOpt3;

    @BindView(R.id.tvOpt4)
    TextView tvOpt4;
    //endregion

    QuizPlayListener mListener;

    private PojoModel mData;
    public QuizPlayFragment() {
        // Required empty public constructor
    }

    public static QuizPlayFragment newInstance() {
        return new QuizPlayFragment();
    }

    public void bind(PojoModel pojoModel) {
        this.mData = pojoModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz_play, container, false);

        ButterKnife.bind(this, v);
        mData.next();
        if (mData.hasNext())
            btnNext.setVisibility(View.VISIBLE);
        else
            btnNext.setVisibility(View.INVISIBLE);
        tvTrueQuestion.setText(mData.getRightQzs().toString());
        tvFalseQuestion.setText(mData.getFalseQzs().toString());
        tvNumber.setText((mData.getCurrentQzNumber() + 1) + "/" + (mData.getData().getQuizModels().size()));
        tvQuiz.setText(mData.getQuestion().getQuestion());
        tvOpt1.setText(mData.getQuestion().getAns1());
        tvOpt2.setText(mData.getQuestion().getAns2());
        tvOpt3.setText(mData.getQuestion().getAns3());
        tvOpt4.setText(mData.getQuestion().getAns4());

        btnNext.setOnClickListener(view -> mListener.gotoNext());

        btnOpt1.setOnClickListener(view -> checkResult(btnOpt1));
        btnOpt2.setOnClickListener(view -> checkResult(btnOpt2));
        btnOpt3.setOnClickListener(view -> checkResult(btnOpt3));
        btnOpt4.setOnClickListener(view -> checkResult(btnOpt4));
        return v;
    }

    Thread t = new Thread() {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
                if (_active)
                    mListener.gotoNext();
                else
                    mListener.gotoFinish();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };


    public void checkResult(ViewGroup view) {
        if (isPressed) return;
        isPressed = true;
        view.setEnabled(false);
        if (view.getTag().toString().equals(mData.getRightAns().toString())) {
            view.setBackground(getResources().getDrawable(R.drawable.button_green));
            mData.updateMark(true);
        } else {
            view.setBackground(getResources().getDrawable(R.drawable.button_red));
            mData.updateMark(false);
            if(btnOpt1.getTag().equals(mData.getRightAns().toString()))
                btnOpt1.setBackground(getResources().getDrawable(R.drawable.button_green));
            else if(btnOpt2.getTag().equals(mData.getRightAns().toString()))
                btnOpt2.setBackground(getResources().getDrawable(R.drawable.button_green));
            else if(btnOpt3.getTag().equals(mData.getRightAns().toString()))
                btnOpt3.setBackground(getResources().getDrawable(R.drawable.button_green));
            else if(btnOpt4.getTag().equals(mData.getRightAns().toString()))
                btnOpt4.setBackground(getResources().getDrawable(R.drawable.button_green));
        }
        tvTrueQuestion.setText(mData.getRightQzs().toString());
        tvFalseQuestion.setText(mData.getFalseQzs().toString());
        _active =mData.hasNext()?true:false;
        t.start();
    }

    public void setListener(QuizPlayListener listener) {
        mListener = listener;
    }

    public interface QuizPlayListener {

        void gotoNext();

        void gotoFinish();
    }
}