package com.onlinestore.corecart.controller;

import com.onlinestore.corecart.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1/orders")
public class OrderController {



    @PostMapping
    public ResponseEntity<Order> createOrder() {


        return null;
    }


}
