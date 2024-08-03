package com.spaceplanner.booking.Global.exceptionhandler;

public class ModelAlreadyExistsException extends RuntimeException {

    public ModelAlreadyExistsException(String message) {
        super(message);
    }
}
