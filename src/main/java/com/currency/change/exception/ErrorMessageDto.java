package com.currency.change.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessageDto {

    private HttpStatus statusCode;
    private String statusError;
    private LocalDateTime dateTime;
    private String message;
}
