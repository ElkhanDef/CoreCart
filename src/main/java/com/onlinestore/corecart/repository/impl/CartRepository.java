package com.onlinestore.corecart.repository.impl;

import com.onlinestore.corecart.model.Cart;
import com.onlinestore.corecart.repository.ICartRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CartRepository implements ICartRepository {

    @PersistenceContext
    private  EntityManager em;



    @Override
    public Cart save(Cart cart) {


        em.persist(cart);

        return cart;
    }

    @Override
    public Optional<Cart> getById(Long id) {

        Cart cart = em.find(Cart.class, id);

        return Optional.ofNullable(cart);
    }

    @Override
    public void delete(Cart cart) {

        em.remove(cart);

    }


}
