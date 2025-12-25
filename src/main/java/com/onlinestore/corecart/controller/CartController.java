package com.onlinestore.corecart.controller;

import com.onlinestore.corecart.dto.CartResponseDto;
import com.onlinestore.corecart.service.impl.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping(path = "/add/{productId}")
    public ResponseEntity<CartResponseDto> addToCart(@PathVariable Long productId,
                                                     @RequestParam int quantity) {
        CartResponseDto response = cartService.addToCart(productId, quantity);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping(path = "/remove/{productId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long productId) {

        cartService.removeFromCart(productId);

        return ResponseEntity.noContent().build();
    }
}
