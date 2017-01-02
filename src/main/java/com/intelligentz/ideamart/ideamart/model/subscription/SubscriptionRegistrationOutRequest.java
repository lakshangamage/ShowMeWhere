package com.intelligentz.ideamart.ideamart.model.subscription;

import com.intelligentz.ideamart.ideamart.model.AbstractOutRequest;

/**
 * Created by lakshan on 12/4/16.
 */
public class SubscriptionRegistrationOutRequest extends AbstractOutRequest {
    private String applicationId;
    private String password;
    private String version;
    private String action;
    private String subscriberId;

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }
}
