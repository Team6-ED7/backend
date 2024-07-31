package com.spaceplanner.booking.Global.controller;


import com.spaceplanner.booking.Global.dto.MessageDto;
import com.spaceplanner.booking.Global.exception.BusinessException;
import com.spaceplanner.booking.Global.exception.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@CrossOrigin(origins = "*")
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<MessageDto> runtimeExceptionHandler(RuntimeException e) {

        //TODO: Implementar log
        MessageDto messageDto = MessageDto.builder().code("500").message(e.getMessage()).build();
        return new ResponseEntity<>(messageDto, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<MessageDto> requestExceptionHandler(RequestException e) {
        MessageDto messageDto = MessageDto.builder().code(e.getCode()).message(e.getMessage()).build();
        return new ResponseEntity<>(messageDto, HttpStatus.BAD_REQUEST);



    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<MessageDto> businessExceptionHandler(BusinessException e) {

        MessageDto messageDto = MessageDto.builder().code(e.getCode()).message(e.getMessage()).build();
        return new ResponseEntity<>(messageDto, HttpStatus.BAD_REQUEST);

    }
}
