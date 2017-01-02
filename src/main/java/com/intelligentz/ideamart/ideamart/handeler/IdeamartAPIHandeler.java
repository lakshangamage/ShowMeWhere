package com.intelligentz.ideamart.ideamart.handeler;

import com.intelligentz.ideamart.exception.IMRequestHandlerException;
import com.intelligentz.ideamart.ideamart.model.AbstractInRequest;
import com.intelligentz.ideamart.ideamart.model.AbstractOutResponse;

/**
 * Created by lakshan on 12/4/16.
 */
public class IdeamartAPIHandeler {
    public AbstractOutResponse handleInRequest(AbstractInRequest inRequest){
        try{
            return inRequest.createResponse(inRequest.handleRequest());
        } catch (IMRequestHandlerException e) {
            return inRequest.createResponse(false);
        }
    }

    public void handleInResponse() {

    }

    public void handleOutRequest() {

    }

    public void handleOutResponse() {

    }
}
