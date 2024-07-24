package com.spaceplanner.booking.Global.dto;



import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class MessageDto {
    private HttpStatus status;
    private String message;
    private Object data;
}
