package com.example.trantien.theflashquiz.mvc.models;

import com.example.trantien.theflashquiz.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by Zuka on 10/2/18.
 */
public class QuestionBankWrapper {
    public static List<QuestionBank> questionBanks;
    public static List<QuestionBank> getAllQuestionBank(){
        if(questionBanks ==null){
            questionBanks = new ArrayList<>();
            questionBanks.add(new QuestionBank.Builder()
                    .Questions(getQuestion1())
                    .Id("F"+Utils.COUNT_FOLDER++)
                    .Owner("Admin")
                    .Topic("Album1")
                    .create());
            questionBanks.add(new QuestionBank.Builder()
                    .Questions(getQuestion1())
                    .Id("F"+Utils.COUNT_FOLDER++)
                    .Owner("Admin")
                    .Topic("Album2")
                    .create());
            questionBanks.add(new QuestionBank.Builder()
                    .Questions(getQuestion1())
                    .Id("F"+Utils.COUNT_FOLDER++)
                    .Owner("Admin")
                    .Topic("Album3")
                    .create());

        }
        return questionBanks;
    }

    public static RealmList<Question> getQuestion1() {
        RealmList mList = new RealmList();
        mList.add(new Question.Builder()
                .Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Hôm nay là thứ mấy?")
                .Ans1("2")
                .Ans2("3")
                .Ans3("4")
                .Ans4("5")
                .Rightans(1)
                .create()
        );
        mList.add(new Question.Builder()
                .Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Hôm nay là ngày mấy?")
                .Ans1("2")
                .Ans2("3")
                .Ans3("4")
                .Ans4("5")
                .Rightans(2)
                .create()
        );
        mList.add(new Question.Builder()
                .Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Hôm nay là tháng mấy?")
                .Ans1("2")
                .Ans2("3")
                .Ans3("4")
                .Ans4("5")
                .Rightans(3)
                .create()
        );
        mList.add(new Question.Builder()
                .Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Hôm nay là tháng mấy?")
                .Ans1("2")
                .Ans2("3")
                .Ans3("4")
                .Ans4("5")
                .Rightans(4)
                .create()
        );
        mList.add(new Question.Builder()
                .Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Hôm nay là tuần mấy?")
                .Ans1("1")
                .Ans2("2")
                .Ans3("3")
                .Ans4("4")
                .Rightans(2)
                .create()
        );
        mList.add(new Question.Builder()
                .Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Hôm nay là năm bao nhiêu?")
                .Ans1("2000")
                .Ans2("3000")
                .Ans3("4000")
                .Ans4("5000")
                .Rightans(1)
                .create()
        );

        return mList;
    }
}
