package com.cityogsadana.bean;

import java.io.Serializable;

/**
 * Created by system2 on 2/15/2018.
 */

public class NotificationBean implements Serializable {

    private boolean isMorningNoti = false;
    private boolean isEveNoti = false;
    private String message;
    private String date;

    private String notificationId;

    private String notificationMsg;

    private String notificationDate;

    public boolean isMorningNoti() {
        return isMorningNoti;
    }

    public void setMorningNoti(boolean morningNoti) {
        isMorningNoti = morningNoti;
    }

    public boolean isEveNoti() {
        return isEveNoti;
    }

    public void setEveNoti(boolean eveNoti) {
        isEveNoti = eveNoti;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationMsg() {
        return notificationMsg;
    }

    public void setNotificationMsg(String notificationMsg) {
        this.notificationMsg = notificationMsg;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }
}
