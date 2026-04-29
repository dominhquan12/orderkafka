package com.develop.orderkafka.strategypattern.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("errorCode", ex.getErrorCode());
        body.put("message", ex.getMessage());

        return ResponseEntity.badRequest().body(body);
    }
}