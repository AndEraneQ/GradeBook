package com.troja.GradeBook.controllers;

import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getCurrentUser(@PathVariable Long id) {
        return userService.getCurrentUser(id);
    }
}
