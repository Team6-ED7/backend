package com.spaceplanner.booking.Global.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class RequestException extends RuntimeException {
    private String code;
    public RequestException(String code, String message) {
        super(message);
        this.code = code;
    }


}
