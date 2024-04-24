package com.currency.change.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException{

    private String codeError;
    private HttpStatus status;

    public BusinessException(String codeError, String message, HttpStatus status){
        super(message);
        this.codeError = codeError;
        this.status = status;
    }
}
