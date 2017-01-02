package com.intelligentz.ideamart.db_model;

/**
 * Created by lakshan on 12/12/16.
 */
public class SubscriptionUsername {
    String subscriberId;
    int pin;
    String username;

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
