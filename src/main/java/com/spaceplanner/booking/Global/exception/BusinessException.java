package com.spaceplanner.booking.Global.exception;

import org.springframework.http.HttpStatus;



public class BusinessException extends RequestException {
    private String code;
    private HttpStatus status;


    public BusinessException(String code, HttpStatus status, String message) {
        super(code, message);
        this.code = code;
        this.status = status;


    }
}
