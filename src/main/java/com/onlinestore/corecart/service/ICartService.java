package com.onlinestore.corecart.service;

import com.onlinestore.corecart.dto.CartResponseDto;

public interface ICartService {

    CartResponseDto addToCart(Long productId, int quantity);
    void removeFromCart(Long productId);
}
