package com.spaceplanner.booking.Global.exceptionhandler;

public class ModelUnauthorizedException extends Exception{

    public ModelUnauthorizedException(String message) {
        super(message);
    }
}
