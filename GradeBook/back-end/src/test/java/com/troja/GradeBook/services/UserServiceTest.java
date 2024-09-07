package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.dto.requests.EditUserDataRequest;
import com.troja.GradeBook.entity.Residence;
import com.troja.GradeBook.entity.Role;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.exception.user.UserNotFoundException;
import com.troja.GradeBook.mapper.UserMapper;
import com.troja.GradeBook.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void testGetCurrentUser_Success() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        UserDto userDto = new UserDto();
        userDto.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userService.getCurrentUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    public void testGetCurrentUser_UserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getCurrentUser(userId));
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(new UserDto());

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toDto(any(User.class))).thenReturn(new UserDto());

        ResponseEntity<List<UserDto>> response = userService.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDtos.size(), response.getBody().size());
    }

    @Test
    public void testDeleteUser_Success() {
        Long userId = 1L;

        when(userRepository.existsById(userId)).thenReturn(true);

        ResponseEntity<?> response = userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User deleted successfully.", response.getBody());
    }

    @Test
    public void testDeleteUser_NotFound() {
        Long userId = 1L;

        when(userRepository.existsById(userId)).thenReturn(false);

        ResponseEntity<?> response = userService.deleteUser(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found.", response.getBody());
    }

    @Test
    public void testEditUserData_Success() {
        EditUserDataRequest request = new EditUserDataRequest();
        request.setUserId(1L);
        request.setPassword("newPassword");
        request.setConfirmPassword("newPassword");
        request.setFirstName("John");
        request.setLastName("Doe");

        User user = new User();
        user.setId(1L);
        Residence residence = new Residence();
        user.setResidence(residence);

        when(userRepository.findById(request.getUserId())).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");

        ResponseEntity<?> response = userService.editUserData(request);

        verify(userRepository, times(1)).save(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated data successfully.", response.getBody());
    }

    @Test
    public void testEditUserData_PasswordsDoNotMatch() {
        EditUserDataRequest request = new EditUserDataRequest();
        request.setPassword("password1");
        request.setConfirmPassword("password2");

        ResponseEntity<?> response = userService.editUserData(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Passwords need to be the same!", response.getBody());
    }

    @Test
    public void testGetStudents() {
        List<User> students = new ArrayList<>();
        User student = new User();
        student.setRole(Role.STUDENT);
        students.add(student);

        List<UserDto> studentDtos = new ArrayList<>();
        studentDtos.add(new UserDto());

        when(userRepository.findByRole(Role.STUDENT)).thenReturn(students);
        when(userMapper.toDto(any(User.class))).thenReturn(new UserDto());

        ResponseEntity<?> response = userService.getStudents();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(studentDtos.size(), ((List<?>) response.getBody()).size());
    }


}
