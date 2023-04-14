package com.academia.ikub.spring.project.domain;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ExceptionResponse {
    private HttpStatus status;
    private Object message;
    private Date timestamp;

    public ExceptionResponse(HttpStatus status, Object message, Date timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }

}
