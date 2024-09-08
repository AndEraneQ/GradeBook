package com.troja.GradeBook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.troja.GradeBook.dto.TeacherSubjectClassDto;
import com.troja.GradeBook.services.TeacherSubjectClassService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TeacherSubjectClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherSubjectClassService teacherSubjectClassService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @WithMockUser
    @Test
    void testFindConnectionByClassId() throws Exception {
        Long classId = 1L;
        String mockResponse = "Connection details for class " + classId;

        doReturn(ResponseEntity.ok(mockResponse)).when(teacherSubjectClassService).findConnectionByClassId(classId);

        mockMvc.perform(get("/api/connection/class/{id}", classId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mockResponse));
    }

    @WithMockUser
    @Test
    void testUpdateConnection() throws Exception {
        TeacherSubjectClassDto requestDto = new TeacherSubjectClassDto();
        String mockResponse = "Connection updated successfully!";

        doReturn(ResponseEntity.ok(mockResponse)).when(teacherSubjectClassService).updateConnection(any(TeacherSubjectClassDto.class));

        mockMvc.perform(put("/api/connection/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(mockResponse));
    }

    @WithMockUser
    @Test
    void testGetClassAndSubjectsOfTeacher() throws Exception {
        Long teacherId = 1L;
        String mockResponse = "Classes and subjects for teacher " + teacherId;

        doReturn(ResponseEntity.ok(mockResponse)).when(teacherSubjectClassService).getAllClassesAndSubjectsLearningByTeacher(teacherId);

        mockMvc.perform(get("/api/teacher/{id}/classes-and-subjects", teacherId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mockResponse));
    }
}
