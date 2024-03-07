package com.euna.university.management.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoRecordFoundException extends RuntimeException{
    public NoRecordFoundException(String error) {
        super(error);
    }
}
