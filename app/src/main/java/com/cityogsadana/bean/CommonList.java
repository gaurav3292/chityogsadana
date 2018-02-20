package com.cityogsadana.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by system2 on 2/20/2018.
 */

public class CommonList implements Serializable {

    private List<NotificationBean> notifications;
    private String notification_count;


    public List<NotificationBean> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationBean> notifications) {
        this.notifications = notifications;
    }

    public String getNotification_count() {
        return notification_count;
    }

    public void setNotification_count(String notification_count) {
        this.notification_count = notification_count;
    }
}
