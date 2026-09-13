package com.orderflow;

import com.orderflow.product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> productNotFoundExceptionHandler(ProductNotFoundException productNotFoundException) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String error = "PRODUCT_NOT_FOUND";
        String message = productNotFoundException.getMessage();
        LocalDateTime dateTime = LocalDateTime.now();
        Map<String, String> errors = new HashMap<>();
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), error, message, dateTime, errors);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(item -> errors.put(item.getField(), item.getDefaultMessage()));
        String error = "VALIDATION_FAILED";
        String message = "Request validation failed";
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), error, message, LocalDateTime.now(), errors);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> runTimeExceptionHandler(RuntimeException runtimeException) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        Map<String, String> errors = new HashMap<>();
        String error = "INTERNAL_SERVER_ERROR";
        String message = "An unexpected error occurred";
        runtimeException.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), error, message, LocalDateTime.now(), errors);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}