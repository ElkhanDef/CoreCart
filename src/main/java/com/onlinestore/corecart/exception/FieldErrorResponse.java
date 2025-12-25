package com.onlinestore.corecart.exception;

public class FieldErrorResponse {

    private String field;
    private  Object rejectedValue;
    private String message;

    public FieldErrorResponse(String field, Object rejectedValue, String message) {
        this.rejectedValue = rejectedValue;
        this.message = message;
        this.field = field;
    }

    public FieldErrorResponse() {}

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
