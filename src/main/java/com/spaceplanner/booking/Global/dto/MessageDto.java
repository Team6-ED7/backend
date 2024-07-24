package com.spaceplanner.booking.Global.dto;



import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class MessageDto {
    private String message;
    private String code;
}
