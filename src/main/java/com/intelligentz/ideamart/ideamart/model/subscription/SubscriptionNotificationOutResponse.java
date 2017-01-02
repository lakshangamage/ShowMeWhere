package com.intelligentz.ideamart.ideamart.model.subscription;

import com.google.gson.Gson;
import com.intelligentz.ideamart.ideamart.model.AbstractOutResponse;

import javax.ws.rs.core.Response;

/**
 * Created by lakshan on 12/6/16.
 */
public class SubscriptionNotificationOutResponse extends AbstractOutResponse{
    private String statusCode;
    private String statusDetail;

    public SubscriptionNotificationOutResponse setStatusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public SubscriptionNotificationOutResponse setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
        return this;
    }
}
