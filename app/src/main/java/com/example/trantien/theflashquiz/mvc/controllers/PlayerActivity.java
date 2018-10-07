package com.example.trantien.theflashquiz.mvc.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;

import com.example.trantien.theflashquiz.R;
import com.example.trantien.theflashquiz.managers.RealmManager;
import com.example.trantien.theflashquiz.mvc.models.QuestionBank;
import com.example.trantien.theflashquiz.utils.Utils;

import butterknife.ButterKnife;
import io.realm.RealmResults;

import static com.example.trantien.theflashquiz.utils.Utils.KEY_QUESTION_BANK_ID;

/**
 * Created by Zuka on 9/18/18.
 */
public class PlayerActivity extends BaseActivity implements QuizPlayFragment.QuizPlayListener {
    private String key;
    private RealmResults<QuestionBank> mList;

    private static final long MOVE_DEFAULT_TIME = 5;
    private static final long FADE_DEFAULT_TIME = 1;

    private FragmentManager mFragmentManager;

    private Handler mDelayedTransactionHandler = new Handler();
    private Runnable mRunnable = this::performTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);

        key = getIntent().getStringExtra(KEY_QUESTION_BANK_ID);
        this.Init();
    }

    private void Init() {
        RealmResults<QuestionBank> mList = RealmManager.getInstance().getQuestionBanks();

        //TODO
        mFragmentManager = getSupportFragmentManager();

        loadInitialFragment();
    }

    private void loadInitialFragment()
    {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, getNewQuiz());
        fragmentTransaction.commit();
    }
int i=0;
    private Fragment getNewQuiz() {
        QuizPlayFragment initialFragment = QuizPlayFragment.newInstance();
        initialFragment.init(i++ +"");
        initialFragment.setListener(this);
        return initialFragment;
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mDelayedTransactionHandler.removeCallbacks(mRunnable);
    }

    private void performTransition()
    {
        if (isDestroyed())
        {
            return;
        }
        Fragment previousFragment = mFragmentManager.findFragmentById(R.id.fragment_container);
        Fragment nextFragment = getNewQuiz();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        // 1. Exit for Previous Fragment
        Fade exitFade = new Fade();
        exitFade.setDuration(FADE_DEFAULT_TIME);
        previousFragment.setExitTransition(exitFade);

        // 2. Shared Elements Transition
        TransitionSet enterTransitionSet = new TransitionSet();
        enterTransitionSet.addTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.move));
        enterTransitionSet.setDuration(MOVE_DEFAULT_TIME);
        enterTransitionSet.setStartDelay(FADE_DEFAULT_TIME);
        nextFragment.setSharedElementEnterTransition(enterTransitionSet);

        // 3. Enter Transition for New Fragment
        Fade enterFade = new Fade();
        enterFade.setStartDelay(MOVE_DEFAULT_TIME + FADE_DEFAULT_TIME);
        enterFade.setDuration(FADE_DEFAULT_TIME);
        nextFragment.setEnterTransition(enterFade);
//        View logo = ButterKnife.findById(this, R.id.fragment1_logo);
//        fragmentTransaction.addSharedElement(logo, logo.getTransitionName());
        fragmentTransaction.replace(R.id.fragment_container, nextFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        back(0, 0);
    }

    private void back(Integer rightNum, Integer score) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(Utils.KEY_RETURN_SCORE , rightNum.toString() + "#" + score.toString());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void gotoNext() {
        mDelayedTransactionHandler.postDelayed(mRunnable, 1);
    }
}
