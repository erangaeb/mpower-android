package com.mpower.power.pojos;

/**
 * Created with IntelliJ IDEA.
 * User: eranga
 * Date: 1/23/14
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationMessage {

    String tickerText;
    String notificationText;

    public NotificationMessage(String tickerText, String notificationText) {
        this.tickerText = tickerText;
        this.notificationText = notificationText;
    }

    public String getTickerText() {
        return tickerText;
    }

    public void setTickerText(String tickerText) {
        this.tickerText = tickerText;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }
}
