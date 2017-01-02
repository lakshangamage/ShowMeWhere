package com.intelligentz.ideamart.ideamart.model;

import com.google.gson.JsonObject;
import com.intelligentz.ideamart.exception.IMRequestHandlerException;

/**
 * Created by lakshan on 12/4/16.
 */
public abstract class AbstractInRequest {

    public AbstractInRequest(){}

    public AbstractInRequest(JsonObject jsonRequest){
        createRequest(jsonRequest);
    }

    protected abstract void createRequest(JsonObject jsonRequest);

    public abstract AbstractOutResponse createResponse(boolean isSuccess);

    public abstract boolean handleRequest() throws IMRequestHandlerException;
}
