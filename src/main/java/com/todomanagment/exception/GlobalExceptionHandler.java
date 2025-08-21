package com.todomanagment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TodoApiException.class)
    public ResponseEntity<ErrorDetail> handleTodoApiException(TodoApiException ex,WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);
    }

}
