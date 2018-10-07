package com.example.trantien.theflashquiz.mvc.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Zuka on 9/25/18.
 */
public class QuestionBank extends RealmObject {
    @PrimaryKey
    private String id;
    private String owner;
    private String topic;
    private RealmList<Question> questions;

    public QuestionBank() {
    }

    public QuestionBank(String id, String owner, String topic, RealmList<Question> questions) {
        this.id = id;
        this.owner = owner;
        this.topic = topic;

        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getTopic() {
        return topic;
    }

    public RealmList<Question> getQuestions() {
        return questions;
    }

    static class Builder {
        private String id;
        private String owner;
        private String topic;
        private RealmList<Question> questions;

        public Builder() {
            this.id = "";
            this.owner = "";
            this.topic = "";
        }

        public Builder Id(String id) {
            this.id = id;
            return this;
        }

        public Builder Owner(String owner) {
            this.owner = owner;
            return this;
        }

        public Builder Topic(String topic) {
            this.topic = topic;
            return this;
        }

        public Builder Questions(RealmList<Question> questions) {
            this.questions = questions;
            return this;
        }

        public QuestionBank create() {
            return new QuestionBank(id, owner, topic, questions);
        }
    }

}
