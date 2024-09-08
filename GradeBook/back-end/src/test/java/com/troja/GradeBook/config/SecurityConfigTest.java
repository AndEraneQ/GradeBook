package com.troja.GradeBook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.troja.GradeBook.dto.AuthenticateDto;
import com.troja.GradeBook.security.jwt.AuthEntryPointJwt;
import com.troja.GradeBook.security.jwt.JwtAuthFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthenticationProvider authenticationProvider;

    @Mock
    private JwtAuthFilter jwtAuthFilter;

    @Mock
    private AuthEntryPointJwt authEntryPointJwt;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private SecurityConfig securityConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenStudentUser_whenAccessingSecuredEndpoint_thenSuccess() throws Exception {
        mockMvc.perform(get("/api/secured")
                        .with(user("student").roles("STUDENT")))
                .andExpect(status().isOk());
    }

    @Test
    void givenAnonymousUser_whenAccessingSecuredEndpoint_thenUnauthorized() throws Exception {
        mockMvc.perform(get("/api/secured"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void givenAdminUser_whenAccessingSecuredEndpoint_thenUnauthorized() throws Exception {
        mockMvc.perform(get("/api/secured")
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isForbidden());
    }
}