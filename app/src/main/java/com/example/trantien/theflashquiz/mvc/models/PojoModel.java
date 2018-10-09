package com.example.trantien.theflashquiz.mvc.models;

/**
 * Created by Zuka on 10/7/18.
 */
public class PojoModel {
    private Integer currentQzNumber;
    private QuizBank data;
    private Integer rights;
    private Integer wrongs;

    public PojoModel(QuizBank data) {
        this.data = data;
        this.currentQzNumber = -1;
        this.rights = 0;
        this.wrongs = 0;
    }

    public QuizModel getQuestion() {
        return data.getQuizModels().get(currentQzNumber);
    }

    public void next() {
        currentQzNumber++;
    }

    public Boolean hasNext() {
        if (data.getQuizModels().size() > currentQzNumber+1)
            return true;
        return false;
    }

    public void updateMark(boolean isRight) {
        if (isRight)
            rights++;
        else
            wrongs++;
    }

    public Integer getRightQzs() {
        return rights;
    }

    public Integer getFalseQzs() {
        return wrongs;
    }

    public Integer getRightAns(){
        return data.getQuizModels().get(currentQzNumber).getRightAns();
    }

    public QuizBank getData() {
        return data;
    }

    public Integer getCurrentQzNumber() {
        return currentQzNumber;
    }
}
