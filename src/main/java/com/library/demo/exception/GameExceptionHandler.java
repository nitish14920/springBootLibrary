package com.library.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GameExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<GameErrorResponse> handleException(Exception exc){

        GameErrorResponse error = new GameErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),System.currentTimeMillis());

        return new ResponseEntity<GameErrorResponse>(error,HttpStatus.BAD_REQUEST);
    }
}
