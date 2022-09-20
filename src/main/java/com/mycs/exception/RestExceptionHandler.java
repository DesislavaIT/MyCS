package com.mycs.exception;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.mycs.entities.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ClientNotFoundException.class)
    protected ResponseEntity<ApiErrorResponse> handleClientNotFound(ClientNotFoundException ex, WebRequest request) {
        ApiErrorResponse apiResponse = new ApiErrorResponse
                .ApiErrorResponseBuilder()
                .withMessage(ex.getMessage() + " ClientNotFoundException")
                .withStatus(HttpStatus.NOT_FOUND)
                .atTime(LocalDateTime.now(ZoneOffset.UTC))
                .build();
        return new ResponseEntity <> (apiResponse, HttpStatus.NOT_FOUND);
    }
}
