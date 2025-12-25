package com.onlinestore.corecart.controller;

import com.onlinestore.corecart.dto.ProductCreateDto;
import com.onlinestore.corecart.dto.ProductListResponseDto;
import com.onlinestore.corecart.dto.ProductResponseDto;
import com.onlinestore.corecart.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<List<ProductListResponseDto>> getAll() {

        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping(path = "/public/{slug}")
    public ResponseEntity<ProductResponseDto> getBySlug(@PathVariable String slug) {

        return ResponseEntity.ok(productService.getBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> save( @Valid @RequestBody ProductCreateDto productCreateDto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.save(productCreateDto));


    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
