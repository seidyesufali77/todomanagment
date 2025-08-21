package com.todomanagment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class TodoApiException extends RuntimeException{
    // Custom exception class for Todo API
    private HttpStatus httpStatus;
    private String message;
    public TodoApiException(HttpStatus httpStatus, String message) {
        super(message); // important so RuntimeException stores the message
        this.httpStatus = httpStatus;
        this.message = message;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


}
