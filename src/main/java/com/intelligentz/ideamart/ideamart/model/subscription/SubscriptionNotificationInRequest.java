package com.intelligentz.ideamart.ideamart.model.subscription;

import com.google.gson.JsonObject;
import com.intelligentz.ideamart.constants.IdeamartAPI;
import com.intelligentz.ideamart.contoller.SubscriptionController;
import com.intelligentz.ideamart.db_model.SubscriptionUsername;
import com.intelligentz.ideamart.exception.IMRequestHandlerException;
import com.intelligentz.ideamart.handeler.SubscriptionHandeler;
import com.intelligentz.ideamart.ideamart.model.AbstractInRequest;
import com.intelligentz.ideamart.ideamart.model.AbstractOutResponse;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import javax.ws.rs.core.Response;
import java.beans.PropertyVetoException;
import java.io.IOException;

/**
 * Created by lakshan on 12/4/16.
 */
public class SubscriptionNotificationInRequest extends AbstractInRequest {

    private String applicationId;
    private String frequency;
    private String status;
    private String subscriberId;
    private String version;
    private String timeStamp;
    private static final String STATUS_REGISTERED = "REGISTERED";
    private static final String STATUS_UNREGISTERED = "UNREGISTERED";

    public SubscriptionNotificationInRequest(){}

    public SubscriptionNotificationInRequest(JsonObject jsonRequest) {
        super(jsonRequest);
    }

    @Override
    protected void createRequest(JsonObject jsonRequest) {
        this.applicationId = jsonRequest.get("applicationId").getAsString();
        this.frequency = jsonRequest.get("frequency").getAsString();
        this.status = jsonRequest.get("status").getAsString();
        this.subscriberId = jsonRequest.get("subscriberId").getAsString();
        this.version = jsonRequest.get("version").getAsString();
        this.timeStamp = jsonRequest.get("timeStamp").getAsString();
    }

    @Override
    public AbstractOutResponse createResponse(boolean isSuccess) {
        AbstractOutResponse outResponse = new SubscriptionNotificationOutResponse().
                setStatusCode(IdeamartAPI.SUCCESS_CODE).
                setStatusDetail(IdeamartAPI.STATUS_DETAIL_SUCCESS);
        outResponse.setStatus(Response.Status.OK);
        return outResponse;
    }

    @Override
    public boolean handleRequest() throws IMRequestHandlerException{
        boolean isSuccess;
        SubscriptionUsername subscriptionUsername = null;
        try {
            SubscriptionHandeler subscriptionHandeler = new SubscriptionHandeler();
            SubscriptionNotificationInRequest currentSubscription =
                    subscriptionHandeler.getSubscription(this);

            if (currentSubscription != null) {
                if (!currentSubscription.getStatus().equals(this.status)) {
                    if (this.status.equals(STATUS_UNREGISTERED)) {
                        currentSubscription.setStatus(STATUS_REGISTERED);
                        subscriptionUsername = subscriptionHandeler.registerExistingSubscriber(currentSubscription);
                    } else {
                        currentSubscription.setStatus(STATUS_UNREGISTERED);
                        subscriptionHandeler.registerExistingSubscriber(currentSubscription);
                        return true;
                    }
                }
            } else {
                subscriptionUsername = new SubscriptionUsername();
                subscriptionUsername.setPin(subscriptionHandeler.addSubscription(this));
            }
            //TODO send registration sms to subscriber
            isSuccess = true;
        } catch (IOException e) {
            throw new IMRequestHandlerException("Couldn't add the subscription to database. ID: " + subscriberId + e.getMessage(), e);
        } catch (PropertyVetoException e) {
            throw new IMRequestHandlerException("Couldn't add the subscription to database ID: " + subscriberId + e.getMessage(), e);
        }
        return isSuccess;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getStatus() {
        return status;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getVersion() {
        return version;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
