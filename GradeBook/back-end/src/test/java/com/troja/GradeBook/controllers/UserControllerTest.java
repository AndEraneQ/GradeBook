package com.troja.GradeBook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.troja.GradeBook.dto.UserDto;
import com.troja.GradeBook.dto.requests.EditUserDataRequest;
import com.troja.GradeBook.services.UserService;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @WithMockUser
    @Test
    void testGetCurrentUser() throws Exception {
        Long userId = 1L;
        UserDto mockUser = new UserDto();

        doReturn(ResponseEntity.ok(mockUser)).when(userService).getCurrentUser(userId);

        mockMvc.perform(get("/api/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockUser)));
    }

    @WithMockUser
    @Test
    void testGetAllUsers() throws Exception {
        List<UserDto> mockUsers = Collections.singletonList(new UserDto());

        doReturn(ResponseEntity.ok(mockUsers)).when(userService).getAllUsers();

        mockMvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockUsers)));
    }

    @WithMockUser
    @Test
    void testDeleteUser() throws Exception {
        Long userId = 1L;
        String mockResponse = "User deleted successfully!";

        doReturn(ResponseEntity.ok(mockResponse)).when(userService).deleteUser(userId);

        mockMvc.perform(delete("/api/delete/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mockResponse));
    }

    @WithMockUser
    @Test
    void testEditUserData() throws Exception {
        EditUserDataRequest request = new EditUserDataRequest(
                1L,
                "Joe",
                "Doe",
                "Cracow",
                "Jana Pawła",
                1L,
                1L,
                "1234",
                "1234"
        );
        String mockResponse = "User data updated successfully!";

        doReturn(ResponseEntity.ok(mockResponse)).when(userService).editUserData(any(EditUserDataRequest.class));

        mockMvc.perform(put("/api/edit/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(mockResponse));
    }

    @WithMockUser
    @Test
    void testGetStudents() throws Exception {
        String mockResponse = "List of students";

        doReturn(ResponseEntity.ok(mockResponse)).when(userService).getStudents();

        mockMvc.perform(get("/api/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mockResponse));
    }
}
