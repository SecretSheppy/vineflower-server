package com.secretsheppy.vineflowerserver.controllers;

import com.secretsheppy.vineflowerserver.exceptions.ApiException;
import com.secretsheppy.vineflowerserver.responses.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> apiException(ApiException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), e.getStatusCode());
    }
}
