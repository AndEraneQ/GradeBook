package com.troja.GradeBook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.dto.requests.AddClassRequest;
import com.troja.GradeBook.dto.requests.EditClassRequest;
import com.troja.GradeBook.security.jwt.JwtUtils;
import com.troja.GradeBook.services.ClassroomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
class ClassroomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClassroomService classroomService;

    @MockBean
    private JwtUtils jwtUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @WithMockUser
    @Test
    void testFindAllClasses() throws Exception {
        doReturn(ResponseEntity.ok("All classes found")).when(classroomService).findAllClasses();

        mockMvc.perform(get("/api/classes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("All classes found"));
    }

    @WithMockUser
    @Test
    void testAddClass() throws Exception {
        AddClassRequest request = new AddClassRequest(
                "Class A",
                new TeacherDto(
                        1L,
                        "testemail@gmail.com",
                        "John",
                        "Doe"
                )
        );

        String reqBody = objectMapper.writeValueAsString(request);

        doReturn(ResponseEntity.ok("Added class correctly!"))
                .when(classroomService)
                .addClass(anyString(), any(TeacherDto.class));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/add/class")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(reqBody)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Added class correctly!"));
    }

    @WithMockUser
    @Test
    void testFindStudentsOfClass() throws Exception {
        Long classId = 1L;
        doReturn(ResponseEntity.ok("Students found")).when(classroomService).findStudentsOfClass(classId);

        mockMvc.perform(get("/api/find/students/{id}", classId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Students found"));
    }

    @WithMockUser
    @Test
    void testDeleteClass() throws Exception {
        Long classId = 1L;
        doReturn(ResponseEntity.ok("Deleted class correctly!")).when(classroomService).deleteClass(classId);

        mockMvc.perform(delete("/api/delete/classroom/{id}", classId)
                        .with(csrf()) // Add CSRF token
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted class correctly!"));
    }

    @WithMockUser
    @Test
    void testEditClassroom() throws Exception {
        EditClassRequest request = new EditClassRequest();

        doReturn(ResponseEntity.ok("Updated data correctly!")).when(classroomService).editClassroom(any(EditClassRequest.class));

        mockMvc.perform(put("/api/edit/classroom")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Updated data correctly!"));
    }
}
