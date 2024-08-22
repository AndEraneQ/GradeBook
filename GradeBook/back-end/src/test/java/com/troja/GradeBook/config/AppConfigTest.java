package com.troja.GradeBook.config;

import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.repository.UserRepository;
import com.troja.GradeBook.security.UserDetails.UserDetailsImpl;
import com.troja.GradeBook.security.UserDetails.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class AppConfigTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AppConfig appConfig;

    private UserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDetailsService = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    void shouldLoadUserByUsername() {
        User user = new User();
        user.setEmail("Johny@gmail.com");
        user.setPassword("$2y$10$k.TOAy03RYro0JEsS7OV7OjRbX3cMzbGg3BzDbhg.sGBricwzoUCu");
        when(userRepository.findByEmail("Johny@gmail.com")).thenReturn(Optional.of(user));

        UserDetailsImpl userDetails = (UserDetailsImpl)
                userDetailsService.loadUserByUsername("Johny@gmail.com");

        assertNotNull(userDetails);
        assertEquals("Johny@gmail.com",userDetails.getUsername());
    }

    @Test
    void passwordEncoderBeanShouldBeCreated() {
        BCryptPasswordEncoder passwordEncoder = appConfig.passwordEncoder();
        assertNotNull(passwordEncoder);
    }

    @Test
    void authenticationProviderBeanShouldBeCreated() {
        AuthenticationProvider authenticationProvider = appConfig.authenticationProvider();
        assertNotNull(authenticationProvider);
    }
}