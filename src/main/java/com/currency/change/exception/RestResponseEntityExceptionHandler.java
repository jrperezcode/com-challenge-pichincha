package com.currency.change.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorMessageDto> businessExceptionHandler(BusinessException ex){
        ErrorMessageDto error = ErrorMessageDto.builder()
                .statusCode(ex.getStatus())
                .statusError(ex.getCodeError())
                .message(ex.getMessage())
                .dateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDto> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessageDto error = ErrorMessageDto.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .statusError("ERROR_GENERAL")
                .message(ex.getMessage())
                .dateTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<ErrorMessageDto>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
