package com.example.springBootFirst.service;

import com.example.springBootFirst.Entity.User;
import com.example.springBootFirst.Repository.UserEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomerUserDetailsServiceImpl implements UserDetailsService {


    UserEntryRepo userEntryRepo;

    public CustomerUserDetailsServiceImpl(UserEntryRepo userEntryRepo) {
        this.userEntryRepo = userEntryRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userEntryRepo.findByUserName(username);
        if (user != null){
           return   org.springframework.security.core
                    .userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
        }
        throw new UsernameNotFoundException("Iser not found with username: " +username);
    }
}
