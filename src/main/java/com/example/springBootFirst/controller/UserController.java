package com.example.springBootFirst.controller;

import com.example.springBootFirst.Entity.User;
import com.example.springBootFirst.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }


    // Update an existing user by username
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        if (userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
            return ResponseEntity.ok(userInDb); //200 ok
        }
        //404 not found
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User with username '" + userName + "' not found.");
    }

    // Delete a user by ID
    @DeleteMapping("/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable String userName) {
        User userInDb = userService.findByUserName(userName);

        if (userInDb != null) {
            ObjectId userId = userInDb.getId();  // Assuming User has an ObjectId field for ID
            try {
                userService.deleteById(userId);  // Call the service method to delete by ObjectId
                return ResponseEntity.noContent().build();  // Successfully deleted
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User with ID '" + userId + "' not found.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User with username '" + userName + "' not found.");
    }


}
