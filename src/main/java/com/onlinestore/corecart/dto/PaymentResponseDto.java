package com.onlinestore.corecart.dto;

import java.math.BigDecimal;

public class PaymentResponseDto {

    private String paymentStatus;
    private String sessionId;
    private BigDecimal amount;
    private String currency;
    private String message;


    public PaymentResponseDto(String paymentStatus, String sessionId, BigDecimal amount, String currency,String message) {
        this.paymentStatus = paymentStatus;
        this.sessionId = sessionId;
        this.amount = amount;
        this.message = message;
        this.currency = currency;
    }

    public PaymentResponseDto() {}


    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
