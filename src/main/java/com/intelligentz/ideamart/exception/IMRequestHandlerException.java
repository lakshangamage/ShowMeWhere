package com.intelligentz.ideamart.exception;

/**
 * Created by lakshan on 12/6/16.
 */
public class IMRequestHandlerException extends Exception {
    private static final long serialVersionUID = 5650589795022586417L;

    public IMRequestHandlerException(String message) {
        super(message);
    }

    public IMRequestHandlerException(String message, Throwable e) {
        super(message, e);
    }
}
