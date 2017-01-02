package com.intelligentz.ideamart.ideamart.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Created by lakshan on 12/4/16.
 */
public abstract class AbstractOutRequest {

    private String destinationURL;

    public String getRequestJSON(AbstractOutRequest outRequest){
        return new Gson().toJson(outRequest);
    }

    public String getDestinationURL() {
        return destinationURL;
    }

    public void setDestinationURL(String destinationURL) {
        this.destinationURL = destinationURL;
    }
}
