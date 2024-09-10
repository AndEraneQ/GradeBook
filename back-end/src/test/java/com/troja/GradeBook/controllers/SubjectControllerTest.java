package com.troja.GradeBook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.troja.GradeBook.dto.requests.AddSubjectRequest;
import com.troja.GradeBook.dto.requests.EditSubjectRequest;
import com.troja.GradeBook.dto.SubjectDto;
import com.troja.GradeBook.entity.Subject;
import com.troja.GradeBook.services.SubjectService;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectService subjectService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @WithMockUser
    @Test
    void testGetAllSubjects() throws Exception {
        SubjectDto subjectDto = new SubjectDto(1L,"Math",new HashSet<>());
        List<SubjectDto> subjects = Collections.singletonList(subjectDto);

        doReturn(subjects).when(subjectService).getAllSubjects();

        mockMvc.perform(get("/api/subjects")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(subjects)));
    }

    @WithMockUser
    @Test
    void testAddSubject() throws Exception {
        AddSubjectRequest request = new AddSubjectRequest("Math",new ArrayList<>());

        doReturn(ResponseEntity.ok("Subject added successfully!"))
                .when(subjectService)
                .addSubject(any(AddSubjectRequest.class));

        mockMvc.perform(post("/api/add/subject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Subject added successfully!"));
    }

    @WithMockUser
    @Test
    void testEditSubjectData() throws Exception {
        EditSubjectRequest request = new EditSubjectRequest(new Subject("math"),new ArrayList<>(),new ArrayList<>());

        doReturn(ResponseEntity.ok("Subject updated successfully!"))
                .when(subjectService)
                .editSubjectData(any(EditSubjectRequest.class));

        mockMvc.perform(put("/api/edit/subjectData")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Subject updated successfully!"));
    }

    @WithMockUser
    @Test
    void testDeleteSubject() throws Exception {
        Long subjectId = 1L;

        doReturn(ResponseEntity.ok("Subject deleted successfully!"))
                .when(subjectService).deleteSubject(subjectId);

        mockMvc.perform(delete("/api/remove/subject/{id}", subjectId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Subject deleted successfully!"));
    }
}
