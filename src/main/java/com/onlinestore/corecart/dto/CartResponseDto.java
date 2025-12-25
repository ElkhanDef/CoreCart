package com.onlinestore.corecart.dto;

import com.onlinestore.corecart.model.CartItem;

import java.util.List;

public class CartResponseDto {

    private  Long id;
    private List<CartItemResponseDto> cartItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItemResponseDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemResponseDto> cartItems) {
        this.cartItems = cartItems;
    }
}
