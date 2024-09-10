package com.troja.GradeBook.services.IServices;

import com.troja.GradeBook.dto.AuthenticateDto;
import com.troja.GradeBook.dto.requests.RegisterUserRequest;
import com.troja.GradeBook.security.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<LoginResponse> login(AuthenticateDto authenticateDto);
    ResponseEntity<String> register(RegisterUserRequest registerUserRequest);
}
