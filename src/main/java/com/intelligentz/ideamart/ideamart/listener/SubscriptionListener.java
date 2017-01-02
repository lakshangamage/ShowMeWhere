package com.intelligentz.ideamart.ideamart.listener;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intelligentz.ideamart.ideamart.handeler.IdeamartAPIHandeler;
import com.intelligentz.ideamart.ideamart.model.AbstractInRequest;
import com.intelligentz.ideamart.ideamart.model.AbstractOutResponse;
import com.intelligentz.ideamart.ideamart.model.subscription.SubscriptionNotificationInRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * Created by lakshan on 12/3/16.
 */
@Path("subscription")
public class SubscriptionListener {
    @Path("/")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public void onSubscriptionReceived(String textRequest){
        JsonObject jsonRequest = new JsonParser().parse(textRequest).getAsJsonObject();
        AbstractInRequest inRequest = new SubscriptionNotificationInRequest(jsonRequest);
        IdeamartAPIHandeler apiHandeler = new IdeamartAPIHandeler();
        AbstractOutResponse outResponse = apiHandeler.handleInRequest(inRequest);
    }
}
