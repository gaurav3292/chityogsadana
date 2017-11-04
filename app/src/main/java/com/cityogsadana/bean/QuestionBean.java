package com.cityogsadana.bean;

import java.io.Serializable;

/**
 * Created by pc15 on 10/23/2017.
 */

public class QuestionBean implements Serializable {

    public QuestionBean(String question) {
        this.question = question;
    }

    private String question;
    private String answers;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
}
