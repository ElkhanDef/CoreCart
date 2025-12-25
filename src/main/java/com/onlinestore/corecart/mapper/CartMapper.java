package com.onlinestore.corecart.mapper;

import com.onlinestore.corecart.dto.CartResponseDto;
import com.onlinestore.corecart.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {


    CartResponseDto toDto(Cart cart);




}
