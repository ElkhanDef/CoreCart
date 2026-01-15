package com.onlinestore.corecart.security;

import com.onlinestore.corecart.model.User;
import com.onlinestore.corecart.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserRepository userRepository;

    public  CustomUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.getUserByEmail(username)
                .orElseThrow(() ->  new UsernameNotFoundException("User not found with username: " + username));


        return new CustomUserDetails(user);

    }
}
