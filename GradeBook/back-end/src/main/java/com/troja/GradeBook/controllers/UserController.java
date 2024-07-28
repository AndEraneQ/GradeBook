package com.troja.GradeBook.controllers;

import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> getCurrentUser(@PathVariable Long id) {
        return userService.getCurrentUser(id);
    }
}
