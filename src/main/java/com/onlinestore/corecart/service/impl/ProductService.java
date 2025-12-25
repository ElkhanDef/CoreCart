package com.onlinestore.corecart.service.impl;

import com.onlinestore.corecart.dto.ProductCreateDto;
import com.onlinestore.corecart.dto.ProductListResponseDto;
import com.onlinestore.corecart.dto.ProductResponseDto;
import com.onlinestore.corecart.exception.ResourceNotFoundException;
import com.onlinestore.corecart.mapper.ProductMapper;
import com.onlinestore.corecart.model.Product;
import com.onlinestore.corecart.repository.IProductRepository;
import com.onlinestore.corecart.service.IProductService;
import com.onlinestore.corecart.util.SlugUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(IProductRepository productRepository, ProductMapper productMapper) {

        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    @Override
    public ProductResponseDto getById(Long id) {

        Product product = productRepository.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        return productMapper.toDto(product);
    }


    @Override
    public List<ProductListResponseDto> getAll() {

        List<Product> productList = productRepository.getAll();


        return productList
                .stream()
                .map(product
                        -> new ProductListResponseDto(
                        product.getSlug(),
                        product.getName(),
                        product.getPrice()
                )).toList();


    }

    @Override
    public ProductResponseDto getBySlug(String slug) {

        Product product = productRepository.getBySlug(slug).
                orElseThrow(() -> new ResourceNotFoundException("Product not found with Slug: " + slug));

        return productMapper.toDto(product);

    }

    @Override
    @Transactional
    public ProductResponseDto save(ProductCreateDto productCreateDto) {


        Product product = productMapper.toEntity(productCreateDto);

        String slug = createUniqueSlug(SlugUtil.toSlug(productCreateDto.getName()));

        product.setSlug(slug);

        Product savedProduct = productRepository.save(product);


        return productMapper.toDto(savedProduct);


    }

    @Override
    @Transactional
    public void delete(Long id) {

        Product product = productRepository.getById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product not found with id " + id)
                );

        productRepository.delete(product);

    }


    private String createUniqueSlug(String slug) {

        String uniqueSlug = slug;
        int i = 1;

        while (productRepository.ifSlugExists(uniqueSlug)) {
            uniqueSlug = slug + "-" + i;
            i++;
        }

        return uniqueSlug;
    }


}
