package com.example.springBootFirst.service;

import com.example.springBootFirst.Entity.User;
import com.example.springBootFirst.Repository.UserEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private final UserEntryRepo userEntryRepository;

    private static final PasswordEncoder pass = new BCryptPasswordEncoder();

    public UserService(UserEntryRepo userEntryRepository) {
        this.userEntryRepository = userEntryRepository;
    }

    // Save or update a user
    public void saveEntry(User user) {
        if (user == null || user.getUserName() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("User, username, or password cannot be null.");
        }
        userEntryRepository.save(user);
    }
    public void saveNewUser(User user) {
         user.setPassword(pass.encode(user.getPassword()));
         user.setRoles(Arrays.asList("USER","ADMIN"));
         userEntryRepository.save(user);
    }
    public void saveAdmin(User user) {
        user.setPassword(pass.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userEntryRepository.save(user);
    }

    public void saveUser(User user){
        userEntryRepository.save(user);
    }

    public List<User> getAll(){
        return userEntryRepository.findAll();

    }

    // Find a user by ID
    public Optional<User> findById(ObjectId id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null.");
        }
        return userEntryRepository.findById(id);
    }


    // Delete a user by ID
    public void deleteById(ObjectId id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null.");
        }
        if (!userEntryRepository.existsById(id)) {
            throw new IllegalArgumentException("User with the given ID does not exist.");
        }
        userEntryRepository.deleteById(id);
    }


    public User findByUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        return userEntryRepository.findByUserName(userName);
    }

}
