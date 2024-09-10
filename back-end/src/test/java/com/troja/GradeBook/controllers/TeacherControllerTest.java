package com.troja.GradeBook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.services.TeacherService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @WithMockUser
    @Test
    void testGetAllTeachers() throws Exception {
        TeacherDto teacherDto = new TeacherDto(1L, "testemail@gmail.com", "John", "Doe");
        List<TeacherDto> teachers = Collections.singletonList(teacherDto);

        doReturn(ResponseEntity.ok(teachers)).when(teacherService).getAllTeachers();

        mockMvc.perform(get("/api/teachers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(teachers)));
    }

    @WithMockUser
    @Test
    void testGetAllTeachersOfSubject() throws Exception {
        Long subjectId = 1L;
        TeacherDto teacherDto = new TeacherDto(1L, "testemail@gmail.com", "John", "Doe");
        List<TeacherDto> teachers = Collections.singletonList(teacherDto);

        doReturn(ResponseEntity.ok(teachers)).when(teacherService).getAllTeachersOfSubject(subjectId);

        mockMvc.perform(get("/api/teachers/subject/{id}", subjectId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(teachers)));
    }

    @WithMockUser
    @Test
    void testGetTeacherFromUserId() throws Exception {
        Long userId = 1L;
        TeacherDto teacherDto = new TeacherDto(1L, "testemail@gmail.com", "John", "Doe");

        doReturn(ResponseEntity.ok(teacherDto)).when(teacherService).getTeacherFromUserId(userId);

        mockMvc.perform(get("/api/teacher/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(teacherDto)));
    }
}
