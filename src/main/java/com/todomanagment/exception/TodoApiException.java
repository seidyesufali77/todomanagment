package com.todomanagment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter
@Setter
public class TodoApiException extends RuntimeException{
    // Custom exception class for Todo API
    private HttpStatus httpStatus;
    private String message;

}
