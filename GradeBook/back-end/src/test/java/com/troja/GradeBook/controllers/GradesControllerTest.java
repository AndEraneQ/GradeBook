package com.troja.GradeBook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.troja.GradeBook.dto.GradeDto;
import com.troja.GradeBook.dto.requests.AddGradeRequest;
import com.troja.GradeBook.services.GradesService;
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

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GradesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GradesService gradesService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @WithMockUser
    @Test
    void testGetGradesByUserIdAndSubjectId() throws Exception {
        Long userId = 1L;
        Long subjectId = 1L;

        String expectedResponse = "[{\"id\":1,\"grade\":5,\"subjectId\":1,\"userId\":1}]";

        doReturn(ResponseEntity.ok(expectedResponse)).when(gradesService).findByUserIdAndSubjectId(userId, subjectId);

        mockMvc.perform(get("/api/grades/user/{userId}/subject/{subjectId}", userId, subjectId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @WithMockUser
    @Test
    void testEditGrade() throws Exception {
        GradeDto gradeDto = new GradeDto(1L, 5, new Date(), "test"); // Przykładowe dane oceny

        doReturn(ResponseEntity.ok("Grade updated successfully!")).when(gradesService).editGrade(any(GradeDto.class));

        String reqBody = objectMapper.writeValueAsString(gradeDto);

        mockMvc.perform(put("/api/edit/grade")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Grade updated successfully!"));
    }

    @WithMockUser
    @Test
    void testAddGrade() throws Exception {
        AddGradeRequest addGradeRequest = new AddGradeRequest(1L,1L,5,"test");

        doReturn(ResponseEntity.ok("Grade added successfully!")).when(gradesService).addGrade(any(AddGradeRequest.class));

        String reqBody = objectMapper.writeValueAsString(addGradeRequest);

        mockMvc.perform(post("/api/add/grade")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Grade added successfully!"));
    }

    @WithMockUser
    @Test
    void testDeleteGrade() throws Exception {
        Long gradeId = 1L;

        doReturn(ResponseEntity.ok("Grade deleted successfully!")).when(gradesService).deleteGrade(gradeId);

        mockMvc.perform(delete("/api/delete/grade/{id}", gradeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Grade deleted successfully!"));
    }
}
