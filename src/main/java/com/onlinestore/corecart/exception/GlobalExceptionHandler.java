package com.onlinestore.corecart.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(

                ex.getMessage(),
                request.getRequestURI(),
                "NOT_FOUND",
                HttpStatus.NOT_FOUND.value(),
                null

        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);

    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<FieldErrorResponse> fieldErrorResponseList = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(
                error -> {

                    FieldErrorResponse fieldErrorResponse = new FieldErrorResponse(
                            error.getField(),
                            error.getRejectedValue(),
                            error.getDefaultMessage()
                    );

                    fieldErrorResponseList.add(fieldErrorResponse);

                });

        ErrorResponse errorResponse = new ErrorResponse(

                "Validation Failed",
                request.getRequestURI(),
                "BAD_REQUEST",
                HttpStatus.BAD_REQUEST.value(),
                fieldErrorResponseList

        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);

    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {


        ErrorResponse errorResponse = new ErrorResponse(
                //Request could not be processed due to a data conflict
                "Email already exists",
                request.getRequestURI(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                HttpStatus.CONFLICT.value(),
                null
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }


}
