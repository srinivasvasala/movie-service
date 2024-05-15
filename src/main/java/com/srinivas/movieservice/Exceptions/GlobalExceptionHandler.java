package com.srinivas.movieservice.Exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MovieExistEx.class)
    public ResponseEntity<String> handleMovieExistEx(MovieExistEx ex){
        log.error("Failed to add movie"+ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    public ResponseEntity<String> handleMovieNotFound(MovieNotFound ex) {
        log.error("Failed to add movie" + ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }



}
