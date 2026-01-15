package com.onlinestore.corecart.controller;

import com.onlinestore.corecart.dto.PaymentResponseDto;
import com.onlinestore.corecart.service.impl.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;


@RestController
@RequestMapping(path = "v1/payments")
public class PaymentController {


    private final PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(path = "/checkout")
    public ResponseEntity<Map<String, String>> checkout() throws StripeException {

        return ResponseEntity.ok(paymentService.checkout());
    }

    @GetMapping(path = "/success")
    public ResponseEntity<PaymentResponseDto> getPaymentResult(@RequestParam String sessionId) throws StripeException {

        return ResponseEntity.ok(paymentService.getPaymentResult(sessionId));
    }

    @GetMapping(path = "/cancel")
    public ResponseEntity<String> cancel(){

        return ResponseEntity.ok("Checkout was canceled or not completed.");
    }


}
