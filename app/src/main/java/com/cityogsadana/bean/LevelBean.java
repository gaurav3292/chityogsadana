package com.cityogsadana.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by system2 on 11/2/2017.
 */

public class LevelBean implements Serializable{

    private String userLevel;
    private String userSubLevel;
    private int totalNumberOfDays;
    private int completedNumberOfDays;
    private int skippedNumberOfDays;
    private String startDate;
    private String isResult;
    private int isExtraResult;
    private int numberOfTrue;
    private int totalNumberOfQuestions;
    private int attendedNumberOfDays;


    public String getUserSubLevel() {
        return userSubLevel;
    }

    public void setUserSubLevel(String userSubLevel) {
        this.userSubLevel = userSubLevel;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public int getTotalNumberOfDays() {
        return totalNumberOfDays;
    }

    public void setTotalNumberOfDays(int totalNumberOfDays) {
        this.totalNumberOfDays = totalNumberOfDays;
    }

    public int getCompletedNumberOfDays() {
        return completedNumberOfDays;
    }

    public void setCompletedNumberOfDays(int completedNumberOfDays) {
        this.completedNumberOfDays = completedNumberOfDays;
    }

    public int getSkippedNumberOfDays() {
        return skippedNumberOfDays;
    }

    public void setSkippedNumberOfDays(int skippedNumberOfDays) {
        this.skippedNumberOfDays = skippedNumberOfDays;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getIsResult() {
        return isResult;
    }

    public void setIsResult(String isResult) {
        this.isResult = isResult;
    }

    public int getNumberOfTrue() {
        return numberOfTrue;
    }

    public void setNumberOfTrue(int numberOfTrue) {
        this.numberOfTrue = numberOfTrue;
    }

    public int getTotalNumberOfQuestions() {
        return totalNumberOfQuestions;
    }

    public void setTotalNumberOfQuestions(int totalNumberOfQuestions) {
        this.totalNumberOfQuestions = totalNumberOfQuestions;
    }

    public int getAttendedNumberOfDays() {
        return attendedNumberOfDays;
    }

    public void setAttendedNumberOfDays(int attendedNumberOfDays) {
        this.attendedNumberOfDays = attendedNumberOfDays;
    }
}
