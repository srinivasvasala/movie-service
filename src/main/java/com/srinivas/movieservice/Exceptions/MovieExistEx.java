package com.srinivas.movieservice.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

public class MovieExistEx extends RuntimeException{
    private String message;
    public MovieExistEx(String message) {
        super(message);
        this.message = message;
    }
}
