package com.cityogsadana.bean;

import java.util.ArrayList;

/**
 * Created by pc15 on 10/23/2017.
 */

public class SelfTestBean {

    private String heading ;
    private ArrayList<QuestionBean> questionBeanList;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public ArrayList<QuestionBean> getQuestionBeanList() {
        return questionBeanList;
    }

    public void setQuestionBeanList(ArrayList<QuestionBean> questionBeanList) {
        this.questionBeanList = questionBeanList;
    }
}
