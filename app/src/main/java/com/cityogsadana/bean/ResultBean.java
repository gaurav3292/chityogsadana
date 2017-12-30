package com.cityogsadana.bean;

import java.io.Serializable;

/**
 * Created by aa on 12/30/2017.
 */

public class ResultBean implements Serializable {

    private String levelResultId;
    private String levelResultPercent;

    public String getLevelResultId() {
        return levelResultId;
    }

    public void setLevelResultId(String levelResultId) {
        this.levelResultId = levelResultId;
    }

    public String getLevelResultPercent() {
        return levelResultPercent;
    }

    public void setLevelResultPercent(String levelResultPercent) {
        this.levelResultPercent = levelResultPercent;
    }
}
