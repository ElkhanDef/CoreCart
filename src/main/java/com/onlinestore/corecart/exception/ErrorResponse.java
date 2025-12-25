package com.onlinestore.corecart.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

    private String message;
    private LocalDateTime timestamp;
    private String path;
    private int code;
    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldErrorResponse> fieldErrors;

    public ErrorResponse(String message, String path, String status,int code, List<FieldErrorResponse> fieldErrors) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.status = status;
        this.code = code;
        this.fieldErrors = fieldErrors;
    }

    public ErrorResponse(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<FieldErrorResponse> getFieldErrors() {
        return fieldErrors;
    }
    public void setFieldErrors(List<FieldErrorResponse> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
