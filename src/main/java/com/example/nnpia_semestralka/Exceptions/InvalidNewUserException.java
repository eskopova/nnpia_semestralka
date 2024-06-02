package com.example.nnpia_semestralka.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidNewUserException extends RuntimeException {
    public InvalidNewUserException(String message) {
        super(message);
    }
}
