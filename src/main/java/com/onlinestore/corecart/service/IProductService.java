package com.onlinestore.corecart.service;

import com.onlinestore.corecart.dto.ProductCreateDto;
import com.onlinestore.corecart.dto.ProductListResponseDto;
import com.onlinestore.corecart.dto.ProductResponseDto;
import com.onlinestore.corecart.model.Product;

import java.util.List;

public interface IProductService {

    ProductResponseDto getById(Long id);

    List<ProductListResponseDto> getAll();

    ProductResponseDto getBySlug(String slug);

    ProductResponseDto save(ProductCreateDto productCreateDto);

    void delete(Long id);

}
