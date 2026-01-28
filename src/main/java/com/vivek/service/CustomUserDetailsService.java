package com.vivek.service;

import com.vivek.entity.CarUser;
import com.vivek.repository.CarUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CarUserRepository carUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email){
        // here our email treated as username
        CarUser user= carUserRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found with this email."));
        return User
                .withUsername(user.getEmail()) // email is userName
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
