package com.spaceplanner.booking.Global.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter

public class BusinessException extends RequestException {
    private HttpStatus status;

    public BusinessException(String code, HttpStatus status, String message) {
        super(code, message);
        this.status = status;
    }


}