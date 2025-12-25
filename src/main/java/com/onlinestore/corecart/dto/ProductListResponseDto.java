package com.onlinestore.corecart.dto;

import java.math.BigDecimal;

public class ProductListResponseDto {

    private String slug;
    private String name;
    private BigDecimal price;


    public ProductListResponseDto() {

    }
    public ProductListResponseDto(String slug, String name, BigDecimal price) {
        this.slug = slug;
        this.name = name;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
