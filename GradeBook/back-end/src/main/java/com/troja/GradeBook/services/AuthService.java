package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.LoginUserDto;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.repository.UserRepository;
import com.troja.GradeBook.security.LoginResponse;
import com.troja.GradeBook.security.UserDetails.UserDetailsImpl;
import com.troja.GradeBook.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public ResponseEntity<LoginResponse> login(LoginUserDto loginUserDto) {
        User authenticatedUser;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUserDto.getEmail(),
                            loginUserDto.getPassword()
                    )
            );
            authenticatedUser = userRepository.findByEmail(loginUserDto.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials", e);
        }

        UserDetails userDetails = new UserDetailsImpl(authenticatedUser);
        String jwtToken = jwtUtils.generateToken(userDetails);
        LoginResponse loginResponse = new LoginResponse(
                authenticatedUser.getId(),
                jwtToken,
                authenticatedUser.getFirstName(),
                authenticatedUser.getLastName(),
                authenticatedUser.getEmail(),
                authenticatedUser.getRole().getName()
        );

        return ResponseEntity.ok(loginResponse);
    }
}

