package com.spaceplanner.booking.Global.exceptionhandler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private HttpStatus status;
    private String message;
    private String url;
    private LocalDateTime date = LocalDateTime.now();

    public ErrorMessage(HttpStatus status, String message, String url) {
        this.status = status;
        this.message = message;
        this.url = url.replace("uri=","");
    }
}
