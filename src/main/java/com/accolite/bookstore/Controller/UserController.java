package com.accolite.bookstore.Controller;

import com.accolite.bookstore.Model.User;
import com.accolite.bookstore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    private ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(this.userService.getUsers());
    }

    @GetMapping("/users/{userId}")
    private User getUserById(@PathVariable long userId){
        return this.userService.getUserById(userId);
    }

    @PostMapping("/users")
    private ResponseEntity<User> saveUser(@RequestBody User user){
        return ResponseEntity.ok().body(this.userService.createUser(user));
    }

    @PutMapping("/users/{userId}")
    private ResponseEntity<User> updateUser(@PathVariable long userId, @RequestBody User user){
        user.setUserId(userId);
        return ResponseEntity.ok().body(this.userService.updateUser(user));
    }

    @PutMapping("/users/{userId}/suspend")
    private ResponseEntity<User> suspendUser(@PathVariable long userId, @RequestBody User user){
        user.setUserId(userId);
        return ResponseEntity.ok().body(this.userService.suspendUser(user));
    }
}
