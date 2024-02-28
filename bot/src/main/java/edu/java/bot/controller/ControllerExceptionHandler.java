package edu.java.bot.controller;

import edu.java.model.errorhandling.ApiErrorResponseBuilder;
import edu.java.model.errorhandling.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value
        = { IllegalArgumentException.class, IllegalStateException.class
    })
    public ResponseEntity<ApiErrorResponse> handleException(Exception e) {
        return ResponseEntity.badRequest()
            .body(ApiErrorResponseBuilder.buildErrorResponse(
                HttpStatus.BAD_REQUEST.toString(), e));
    }
}
