package com.onlinestore.corecart.service;

import com.onlinestore.corecart.dto.UserCreateDto;

public interface IUserService {

    void save(UserCreateDto userCreateDto);

    void delete(Long id);

}
