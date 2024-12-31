package com.example.banking.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.banking.repo.UserRepo;

public class MyUserService implements UserDetailsService {

    public UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByPhNo(Long.parseLong(username)).get();

    }
    
}
