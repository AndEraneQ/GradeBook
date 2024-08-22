package com.troja.GradeBook.security.UserDetails;

import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
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
    void shouldThrowUsernameNotFoundException() {
        when(userRepository.findByEmail("Johny@gmail.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("Johny@gmail.com");
        });
    }
}