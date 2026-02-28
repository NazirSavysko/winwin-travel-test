package com.winwin.travel.authapi.controller;

import com.winwin.travel.authapi.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public final class GlobalControllerAdvice {


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleUserAlreadyExistsException(final UserAlreadyExistsException exception) {

       final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
               HttpStatus.CONFLICT,
                exception.getMessage()
        );

        problemDetail.setTitle("User Already Exists");

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(problemDetail);
    }
}
