package com.onlinestore.corecart.service.impl;

import com.onlinestore.corecart.dto.UserCreateDto;
import com.onlinestore.corecart.enums.RoleNames;
import com.onlinestore.corecart.exception.ResourceNotFoundException;
import com.onlinestore.corecart.model.Address;
import com.onlinestore.corecart.model.Role;
import com.onlinestore.corecart.model.User;
import com.onlinestore.corecart.repository.IAddressRepository;
import com.onlinestore.corecart.repository.IRoleRepository;
import com.onlinestore.corecart.repository.IUserRepository;
import com.onlinestore.corecart.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;


    public UserService(PasswordEncoder passwordEncoder,
                       IUserRepository userRepository,
                       IRoleRepository roleRepository,
                       IAddressRepository addressRepository) {

        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    @Transactional
    public void save(UserCreateDto userDto) {

        Role role = roleRepository.getRoleByName(RoleNames.CUSTOMER)
                .orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));

        System.out.println(role.getId());


        User user = new User();
        Address address = new Address(
                userDto.getAddress().getAddress(),
                userDto.getAddress().getDistrict(),
                userDto.getAddress().getCity()
        );

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);

        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setRole(role);

        user.setAddress(address);

        userRepository.save(user);

    }

    @Override
    @Transactional
    public void delete(Long id) {

        User user = userRepository.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        userRepository.delete(user);
    }
}
