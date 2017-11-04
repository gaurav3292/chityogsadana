package com.cityogsadana.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by system2 on 11/2/2017.
 */

public class LevelBean implements Serializable{

    private String userLevel;
    private String totalNumberOfDays;
    private String completedNumberOfDays;

    public String getLevel() {
        return userLevel;
    }

    public void setLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getTotalNumberOfDays() {
        return totalNumberOfDays;
    }

    public void setTotalNumberOfDays(String totalNumberOfDays) {
        this.totalNumberOfDays = totalNumberOfDays;
    }

    public String getCompletedNumberOfDays() {
        return completedNumberOfDays;
    }

    public void setCompletedNumberOfDays(String completedNumberOfDays) {
        this.completedNumberOfDays = completedNumberOfDays;
    }
}
