package com.spaceplanner.booking.Global.exceptionhandler;

public class ModelAlreadyExistsException extends Exception {

    public ModelAlreadyExistsException(String message) {
        super(message);
    }
}
