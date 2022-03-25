package com.mto.topquizz.model;

import java.util.List;

public class Question {

    private String Question;
    private List<String> ChoiceList;
    private int AnswerIndex;

    public Question(String mQuestion, List<String> ChoiceList, int AnswerIndex) {
        this.Question = mQuestion;
        this.ChoiceList = ChoiceList;
        this.AnswerIndex = AnswerIndex;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public List<String> getChoiceList() {
        return ChoiceList;
    }

    public void setChoiceList(List<String> ChoiceList) {
        this.ChoiceList = ChoiceList;
    }

    public int getAnswerIndex() {
        return AnswerIndex;
    }

    public void setAnswerIndex(int AnswerIndex) {
        this.AnswerIndex = AnswerIndex;
    }

    @Override
    public String toString() {
        return "Question{" +
                "mQuestion='" + Question + '\'' +
                ", mChoiceList=" + ChoiceList +
                ", mAnswerIndex=" + AnswerIndex +
                '}';
    }
}
