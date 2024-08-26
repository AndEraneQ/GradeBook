package com.troja.GradeBook.controllers;

import com.troja.GradeBook.dto.AuthenticateDto;
import com.troja.GradeBook.dto.requests.RegisterUserRequest;
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
    public ResponseEntity<LoginResponse> login(@RequestBody AuthenticateDto authenticateDto) {
        return authService.login(authenticateDto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequest registerUserRequest){
        return  authService.register(registerUserRequest);
    }
}
