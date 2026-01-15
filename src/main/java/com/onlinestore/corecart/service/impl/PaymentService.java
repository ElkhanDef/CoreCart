package com.onlinestore.corecart.service.impl;

import com.onlinestore.corecart.dto.PaymentResponseDto;
import com.onlinestore.corecart.exception.ResourceNotFoundException;
import com.onlinestore.corecart.model.Cart;
import com.onlinestore.corecart.model.CartItem;
import com.onlinestore.corecart.model.User;
import com.onlinestore.corecart.security.CustomUserDetails;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    public User getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {

            throw new RuntimeException("Authentication required");
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return userDetails.getUser();

    }


    public Map<String, String> checkout() throws StripeException {

        Cart cart = getCurrentUser().getCart();


        if (cart == null) {

            throw new ResourceNotFoundException("Cart is not available");
        }
        if (cart.getCartItems() == null || cart.getCartItems().isEmpty()) {

            throw new ResourceNotFoundException("Cart items are not available");

        }

        List<CartItem> cartItems = cart.getCartItems();


        List<SessionCreateParams.LineItem> lineItems = cartItems.stream().map(cartItem ->
                SessionCreateParams.LineItem.builder()
                        .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("azn")
                                        .setUnitAmount(cartItem.getPrice().multiply(BigDecimal.valueOf(100)).longValueExact())
                                        .setProductData(
                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                        .setName(cartItem.getProduct().getName())
                                                        .build()
                                        )
                                        .build()
                        )
                        .setQuantity((long) cartItem.getQuantity())
                        .build()).toList();


        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl("http://localhost:8080/v1/payments/cancel")
                .setSuccessUrl("http://localhost:8080/v1/payments/success?sessionId={CHECKOUT_SESSION_ID}")
                .addAllLineItem(lineItems)
                .build();

        Session session = Session.create(params);

        return Map.of("SessionId", session.getId(),
                "URL", session.getUrl());

    }


    public PaymentResponseDto getPaymentResult(String sessionId) throws StripeException {

        Session session = Session.retrieve(sessionId);


        String paymentStatus = session.getPaymentStatus();

        String message;

        if (paymentStatus.equals("paid")) {

             message = "Payment has been successfully completed.";
        }
        else if (paymentStatus.equals("unpaid")) {

             message = "Payment has not been completed.";
        }
        else {
             message = "No Payment was required.";
        }



        BigDecimal amount = BigDecimal.valueOf(session.getAmountTotal()).divide(BigDecimal.valueOf(100));

        return new PaymentResponseDto(

                paymentStatus,
                session.getId(),
                amount,
                session.getCurrency(),
                message


        );



    }

}
