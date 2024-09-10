package com.troja.GradeBook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.troja.GradeBook.dto.AuthenticateDto;
import com.troja.GradeBook.dto.requests.RegisterUserRequest;
import com.troja.GradeBook.entity.Role;
import com.troja.GradeBook.security.LoginResponse;
import com.troja.GradeBook.services.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    public AuthControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @WithMockUser
    @Test
    void testLogin() throws Exception {
        AuthenticateDto authenticateDto = new AuthenticateDto(
                "user@example.com",
                "password123"
        );
        LoginResponse loginResponse = new LoginResponse(
                1L,
                "Bearer token",
                "John",
                "Doe",
                "johndoe@gmail.com",
                "1a",
                1L,
                true,
                Role.TEACHER
        );

        doReturn(ResponseEntity.ok(loginResponse)).when(authService).login(any(AuthenticateDto.class));

        String reqBody = objectMapper.writeValueAsString(authenticateDto);

        mockMvc.perform(
                        post("/api/auth")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(reqBody)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(loginResponse)));
    }

    @WithMockUser
    @Test
    void testRegister() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest();

        doReturn(ResponseEntity.ok("User registered successfully!")).when(authService).register(any(RegisterUserRequest.class));

        String reqBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                        post("/api/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(reqBody)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully!"));
    }
}
