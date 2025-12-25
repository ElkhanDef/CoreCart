package com.onlinestore.corecart.repository;

import com.onlinestore.corecart.model.User;

import java.util.Optional;

public interface IUserRepository {


    Optional<User> getUserByEmail(String email);

    Optional<User> getUserById(Long id);

    User save(User user);

    void delete(User user);

}
