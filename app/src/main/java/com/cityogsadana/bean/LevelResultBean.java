package com.cityogsadana.bean;

import java.io.Serializable;

/**
 * Created by aa on 12/30/2017.
 */

public class LevelResultBean implements Serializable{


    private ResultBean result;
    private LevelBean level;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public LevelBean getLevel() {
        return level;
    }

    public void setLevel(LevelBean level) {
        this.level = level;
    }
}
