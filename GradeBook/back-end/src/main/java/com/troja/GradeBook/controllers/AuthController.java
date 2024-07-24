package com.troja.GradeBook.controllers;

import com.troja.GradeBook.dto.LoginUserDto;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.security.LoginResponse;
import com.troja.GradeBook.security.UserDetails.UserDetailsImpl;
import com.troja.GradeBook.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.troja.GradeBook.security.jwt.JwtUtils;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
//        User authenticatedUser = authService.authenticate(loginUserDto);
//        UserDetails userDetails = new UserDetailsImpl(authenticatedUser);
//
//        String jwtToken = jwtUtils.generateToken(userDetails);
//        LoginResponse loginResponse = new LoginResponse(jwtToken,authenticatedUser.getFirstName(),authenticatedUser.getLastName());
//
//        return ResponseEntity.ok(loginResponse);
//    }
@PostMapping("/login")
public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto) {
    return authService.login(loginUserDto);
    }
}
