package com.example.trantien.theflashquiz.mvc.models;

import com.example.trantien.theflashquiz.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by Zuka on 10/2/18.
 */
public class QuizBankWrapper {
    public static List<QuizBank> quizBanks;
    public static List<QuizBank> getAllQuestionBank(){
        if(quizBanks ==null){
            quizBanks = new ArrayList<>();
            quizBanks.add(new QuizBank.Builder()
                    .Questions(getQuestion1())
                    .Id("Option"+Utils.COUNT_FOLDER++)
                    .Owner("Admin")
                    .Topic("Bộ câu hỏi A")
                    .create());
            quizBanks.add(new QuizBank.Builder()
                    .Questions(getQuestion2())
                    .Id("Option"+Utils.COUNT_FOLDER++)
                    .Owner("Admin")
                    .Topic("Bộ câu hỏi B")
                    .create());
            quizBanks.add(new QuizBank.Builder()
                    .Questions(getQuestion3())
                    .Id("Option"+Utils.COUNT_FOLDER++)
                    .Owner("Admin")
                    .Topic("Bộ câu hỏi C")
                    .create());
        }
        return quizBanks;
    }

    public static RealmList<QuizModel> getQuestion1() {
        RealmList mList = new RealmList();
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Nước gì chỉ có đàn ông?")
                .Ans1("Nước Anh").Ans2("Nước Việt Nam").Ans3("Nước Áo").Ans4("Nước Mĩ").Rightans(1).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Trái gì sinh ra đã không nguyên vẹn?")
                .Ans1("Trái chuối").Ans2("Trái cóc").Ans3("Trái măng cụt").Ans4("Trái cam").Rightans(3).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Sữa gì không dùng để uống?")
                .Ans1("Sữa voi").Ans2("Sữa mẹ").Ans3("Sữa Ông Thọ").Ans4("Sữa tắm").Rightans(4).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Bán gì chỉ có một nửa?")
                .Ans1("Bán rau").Ans2("Bán muối").Ans3("Bán hành").Ans4("Bán kính").Rightans(4).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Đậu gì vô tình nhất?")
                .Ans1("Đậu phụ").Ans2("Đậu phộng").Ans3("Đậu rồng").Ans4("Đậu xanh").Rightans(1).create()
        );

        return mList;
    }
    public static RealmList<QuizModel> getQuestion2() {
        RealmList mList = new RealmList();
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Trái gì biết nhảy?")
                .Ans1("Nước Anh").Ans2("Nước Việt Nam").Ans3("Nước Áo").Ans4("Nước Mĩ").Rightans(1).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Trái gì sinh ra đã không nguyên vẹn")
                .Ans1("Trái chuối").Ans2("Trái cóc").Ans3("Trái măng cụt").Ans4("Trái cam").Rightans(3).create()
        );

        return mList;
    }
    public static RealmList<QuizModel> getQuestion3() {
        RealmList mList = new RealmList();
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Nước gì chỉ có đàn ông?")
                .Ans1("Nước Anh").Ans2("Nước Việt Nam").Ans3("Nước Áo").Ans4("Nước Mĩ").Rightans(1).create()
        );
        mList.add(new QuizModel.Builder().Id("Q"+Utils.COUNT_QUESTION++)
                .Question("Trái gì sinh ra đã không nguyên vẹn")
                .Ans1("Trái chuối").Ans2("Trái cóc").Ans3("Trái măng cụt").Ans4("Trái cam").Rightans(3).create()
        );

        return mList;
    }
}
