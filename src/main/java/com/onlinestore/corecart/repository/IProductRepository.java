package com.onlinestore.corecart.repository;

import com.onlinestore.corecart.dto.ProductResponseDto;
import com.onlinestore.corecart.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {

    Optional<Product> getById(Long id);

    List<Product> getAll();

    Optional<Product> getBySlug(String slug);

    Product save(Product product);

    void delete(Product product);

    boolean ifSlugExists(String slug);
}
