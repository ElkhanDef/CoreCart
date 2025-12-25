package com.onlinestore.corecart.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductCreateDto {

    @NotBlank(message = "This field must be set")
    @Size(min = 3, max = 100, message = "Name length must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "This field must be set")
    @Size(min = 5, max = 255, message = "Description length must be between 5 and 255 characters")
    private String description;


    @NotNull(message = "This field must be set")
    @DecimalMin(value = "0.0", message = "Discount cannot be negative")
    private BigDecimal discount;


    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @NotNull(message = "Price can not be null")
    private BigDecimal price;

    @Min(value = 0,message = "Stock can't be negative")
    private int stock;

    public ProductCreateDto(String description, String name, BigDecimal price, int stock,BigDecimal discount) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.discount = discount;
    }
    public ProductCreateDto() {

    }



    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
    public BigDecimal getDiscount() {
        return discount;
    }
}
