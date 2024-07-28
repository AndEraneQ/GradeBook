package com.troja.GradeBook.controllers;

import com.troja.GradeBook.dto.LoginUserDto;

import com.troja.GradeBook.security.LoginResponse;
import com.troja.GradeBook.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto) {
        return authService.login(loginUserDto);
    }
}
