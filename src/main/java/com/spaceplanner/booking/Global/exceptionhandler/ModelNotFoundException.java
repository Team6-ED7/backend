package com.spaceplanner.booking.Global.exceptionhandler;

public class ModelNotFoundException extends RuntimeException {

    public ModelNotFoundException(String message) {
        super(message);
    }
}
