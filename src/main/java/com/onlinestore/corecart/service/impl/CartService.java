package com.onlinestore.corecart.service.impl;

import com.onlinestore.corecart.dto.CartResponseDto;
import com.onlinestore.corecart.exception.ResourceNotFoundException;
import com.onlinestore.corecart.mapper.CartMapper;
import com.onlinestore.corecart.model.Cart;
import com.onlinestore.corecart.model.CartItem;
import com.onlinestore.corecart.model.Product;
import com.onlinestore.corecart.model.User;
import com.onlinestore.corecart.repository.ICartRepository;
import com.onlinestore.corecart.repository.IProductRepository;
import com.onlinestore.corecart.repository.IUserRepository;
import com.onlinestore.corecart.security.CustomUserDetails;
import com.onlinestore.corecart.service.ICartService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CartService implements ICartService {

    private final ICartRepository cartRepository;
    private final IProductRepository productRepository;
    private final IUserRepository userRepository;
    private final CartMapper cartMapper;


    public CartService(ICartRepository cartRepository,
                       IProductRepository productRepository,
                       IUserRepository userRepository,
                       CartMapper cartMapper) {

        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartMapper = cartMapper;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Authentication required");
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();


        return userDetails.getUser();

    }


    @Override
    @Transactional
    public CartResponseDto addToCart(Long productId, int quantity) {

        Cart cart;

        User user = userRepository.getUserById(getCurrentUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        Product product = productRepository.getById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));



        CartItem foundedCartItem;

        if (user.getCart() != null) {

            cart = user.getCart();

            foundedCartItem = cart.getProductInCart(product);

            if (foundedCartItem != null) {
                foundedCartItem.setQuantity(foundedCartItem.getQuantity() + quantity);
                foundedCartItem.setTotalPrice(totalPriceCalculator(foundedCartItem.getPrice(),
                        foundedCartItem.getDiscount(), foundedCartItem.getQuantity()));
            } else {
                CartItem newCartItem = new CartItem();
                newCartItem.setProduct(product);
                newCartItem.setQuantity(quantity);
                newCartItem.setPrice(product.getPrice());
                newCartItem.setDiscount(product.getDiscount());
                newCartItem.setTotalPrice(
                        totalPriceCalculator(newCartItem.getPrice(), newCartItem.getDiscount(), newCartItem.getQuantity())
                );
                cart.addItem(newCartItem);
            }

        } else {
            cart = new Cart();

            CartItem newCartItem = new CartItem();
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            newCartItem.setPrice(product.getPrice());
            newCartItem.setDiscount(product.getDiscount());
            newCartItem.setTotalPrice(
                    totalPriceCalculator(newCartItem.getPrice(), newCartItem.getDiscount(), newCartItem.getQuantity())
            );
            cart.addItem(newCartItem);

        }
        user.assignCart(cart);
        cartRepository.save(cart);

        return cartMapper.toDto(cart);

    }

    @Override
    @Transactional
    public void removeFromCart(Long productId) {
        Product product = productRepository.getById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        User user = userRepository.getUserById(getCurrentUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        Cart cart = user.getCart();

        CartItem cartItem = cart.getProductInCart(product);

        if  (cartItem != null) {
            cart.removeItem(cartItem);
        }
        else {
            throw new ResourceNotFoundException("Product not found in cart");
        }

        cartRepository.save(cart);
    }


    private BigDecimal totalPriceCalculator(BigDecimal productPrice, BigDecimal productDiscount, int quantity) {
        BigDecimal totalPrice;

        totalPrice = productPrice.multiply(BigDecimal.valueOf(quantity)).subtract(productPrice.multiply(BigDecimal.valueOf(quantity)).multiply(productDiscount));

        return totalPrice;

    }

}

