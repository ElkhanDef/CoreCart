package com.onlinestore.corecart.repository;

import com.onlinestore.corecart.model.Cart;

import java.util.Optional;


public interface ICartRepository {

    Cart save(Cart cart);

    Optional<Cart> getById(Long id);

    void delete(Cart cart);

}
