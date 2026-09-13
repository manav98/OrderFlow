package com.orderflow;

import com.orderflow.product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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
        ErrorResponse errorResponse = new ErrorResponse(httpStatus, error, message, dateTime, errors);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        Map<String, String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach(item -> errorMap.put(item.getField(), item.getDefaultMessage()));

        String error = "VALIDATION_FAILED";
        String message = "Request validation failed";
        LocalDateTime dateTime = LocalDateTime.now();
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, error, message, dateTime, errorMap);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}