package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.AuthenticateDto;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.exception.MyCustomException;
import com.troja.GradeBook.repository.UserRepository;
import com.troja.GradeBook.security.LoginResponse;
//import com.troja.GradeBook.security.UserDetails.UserDetailsImpl;
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

    public ResponseEntity<LoginResponse> login(AuthenticateDto authenticateDto) {
        User authenticatedUser;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticateDto.getEmail(),
                            authenticateDto.getPassword()
                    )
            );
            authenticatedUser = userRepository.findByEmail(authenticateDto.getEmail())
                    .orElseThrow(() -> new MyCustomException("email", "User not found"));
        } catch (AuthenticationException e) {
            throw new MyCustomException("password", "Invalid credentials");
        }

        UserDetails userDetails = new UserDetailsImpl(authenticatedUser);
        String jwtToken = jwtUtils.generateToken(userDetails);
        LoginResponse loginResponse = new LoginResponse(
                authenticatedUser.getId(),
                jwtToken,
                authenticatedUser.getFirstName(),
                authenticatedUser.getLastName(),
                authenticatedUser.getEmail(),
                authenticatedUser.getRole()
        );

        return ResponseEntity.ok(loginResponse);
    }
}

