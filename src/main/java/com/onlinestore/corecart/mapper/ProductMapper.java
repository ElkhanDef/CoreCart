package com.onlinestore.corecart.mapper;

import com.onlinestore.corecart.dto.ProductCreateDto;
import com.onlinestore.corecart.dto.ProductResponseDto;
import com.onlinestore.corecart.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mapping(target = "slug",ignore = true)
    Product toEntity(ProductCreateDto productCreateDto);


    ProductResponseDto toDto(Product product);

}
