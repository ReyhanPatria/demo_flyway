package com.example.demo_flyway.controller.rest;

import com.example.demo_flyway.model.entity.User;
import com.example.demo_flyway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> userList = userService.findAll();
        return ResponseEntity.ok(userList);
    }

    @PostMapping("/user")
    public ResponseEntity<User> insertUser(@RequestBody User user) {
        user = userService.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.findById(id);
        return ResponseEntity.of(user);
    }
}