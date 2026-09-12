package com.orderflow;

import com.orderflow.product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> productNotFoundExceptionHandler(ProductNotFoundException productNotFoundException) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String error = "PRODUCT_NOT_FOUND";
        String message = productNotFoundException.getMessage();
        LocalDateTime dateTime = LocalDateTime.now();
        ErrorResponse errorResponse = new ErrorResponse(httpStatus, error, message, dateTime);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

//    @ExceptionHandler()
//    public  ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler (MethodArgumentNotValidException methodArgumentNotValidException)
//    {}

}