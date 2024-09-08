package com.troja.GradeBook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.troja.GradeBook.dto.ResidenceDto;
import com.troja.GradeBook.services.ResidenceService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ResidenceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResidenceService residenceService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @WithMockUser
    @Test
    void testGetUserResidence() throws Exception {
        Long userId = 1L;
        ResidenceDto residenceDto = new ResidenceDto(
                1L,
                "Cracow",
                "Jana Pawła",
                1L,
                1L
        );

        doReturn(ResponseEntity.ok(residenceDto)).when(residenceService).getUserResidence(anyLong());

        mockMvc.perform(get("/api/residence/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(residenceDto)));
    }
}
