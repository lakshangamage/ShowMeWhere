package com.intelligentz.ideamart.ideamart.model.sms;

import com.intelligentz.ideamart.ideamart.model.AbstractOutRequest;

/**
 * Created by lakshan on 12/15/16.
 */
public class SMSSendOutRequest extends AbstractOutRequest{
    private String message;
    private String[] destinationAddresses;
    private String password;
    private String applicationId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getDestinationAddresses() {
        return destinationAddresses;
    }

    public void setDestinationAddresses(String[] destinationAddresses) {
        this.destinationAddresses = destinationAddresses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
