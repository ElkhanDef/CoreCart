package com.onlinestore.corecart.controller;

import com.onlinestore.corecart.dto.UserCreateDto;
import com.onlinestore.corecart.service.impl.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "v1/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(path = "/public/register")
    public ResponseEntity<Void> save(@Valid @RequestBody UserCreateDto userCreateDto) {

        userService.save(userCreateDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        userService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
