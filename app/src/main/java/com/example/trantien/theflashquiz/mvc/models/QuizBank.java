package com.example.trantien.theflashquiz.mvc.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Zuka on 9/25/18.
 */
public class QuizBank extends RealmObject {
    @PrimaryKey
    private String id;
    private String owner;
    private String topic;
    private RealmList<QuizModel> quizModels;

    public QuizBank() {
    }

    public QuizBank(String id, String owner, String topic, RealmList<QuizModel> quizModels) {
        this.id = id;
        this.owner = owner;
        this.topic = topic;

        this.quizModels = quizModels;
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

    public RealmList<QuizModel> getQuizModels() {
        return quizModels;
    }

    static class Builder {
        private String id;
        private String owner;
        private String topic;
        private RealmList<QuizModel> quizModels;

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

        public Builder Questions(RealmList<QuizModel> quizModels) {
            this.quizModels = quizModels;
            return this;
        }

        public QuizBank create() {
            return new QuizBank(id, owner, topic, quizModels);
        }
    }

}
