package com.example.trantien.theflashquiz.mvc.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Zuka on 10/2/18.
 */
public class Question extends RealmObject {
    @PrimaryKey
    private String id;
    private String question;
    private String ans1;
    private String ans2;
    private String ans3;
    private String ans4;
    private int rightAns;

    public Question() {
    }

    public Question(String id, String question, int rightAns, String ans1, String ans2, String ans3, String ans4) {
        this.id = id;
        this.question = question;
        this.rightAns = rightAns;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public int getRightAns() {
        return rightAns;
    }

    public String getAns1() {
        return ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public String getAns4() {
        return ans4;
    }

    static class Builder {
        private String id;
        private String question;
        private int rightAns;
        private String ans1;
        private String ans2;
        private String ans3;
        private String ans4;

        public Builder() {
            this.question = "";
            this.rightAns = 1;
            this.ans1 = "";
            this.ans2 = "";
            this.ans3 = "";
            this.ans4 = "";
        }

        public Builder Id(String id) {
            this.id = id;
            return this;
        }

        public Builder Question(String question) {
            this.question = question;
            return this;
        }

        public Builder Rightans(int rightAns) {
            this.rightAns = rightAns;
            return this;
        }

        public Builder Ans1(String ans1) {
            this.ans1 = ans1;
            return this;
        }

        public Builder Ans2(String ans2) {
            this.ans2 = ans2;
            return this;
        }

        public Builder Ans3(String ans3) {
            this.ans3 = ans3;
            return this;
        }

        public Builder Ans4(String ans4) {
            this.ans4 = ans4;
            return this;
        }

        public Question create() {
            return new Question(id, question, rightAns, ans1, ans2, ans3, ans4);
        }
    }


}
