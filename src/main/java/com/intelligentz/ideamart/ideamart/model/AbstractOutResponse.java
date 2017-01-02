package com.intelligentz.ideamart.ideamart.model;

import com.google.gson.Gson;

import javax.ws.rs.core.Response;

/**
 * Created by lakshan on 12/6/16.
 */
public abstract class AbstractOutResponse {
    protected Response.Status status;

    public Response createResponse(AbstractOutResponse outResponse) {
        return Response.status(status).entity(new Gson().toJson(outResponse)).build();
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }
}
