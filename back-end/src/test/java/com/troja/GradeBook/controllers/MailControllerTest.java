package com.troja.GradeBook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.troja.GradeBook.dto.requests.SendMailRequest;
import com.troja.GradeBook.services.MailService;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MailService mailService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @WithMockUser
    @Test
    void testSendMail() throws Exception {
        SendMailRequest sendMailRequest = new SendMailRequest("fromuser@example.com","touser@example.com", "Subject", "Body");

        doReturn(ResponseEntity.ok("Mail sent successfully!")).when(mailService).sendMail(any(SendMailRequest.class));

        String reqBody = objectMapper.writeValueAsString(sendMailRequest);

        mockMvc.perform(post("/api/send/mail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Mail sent successfully!"));
    }

    @WithMockUser
    @Test
    void testGetReceivedMails() throws Exception {
        String email = "test@example.com";
        String expectedResponse = "[{\"id\":1,\"from\":\"test@example.com\",\"subject\":\"Hello\",\"body\":\"Hi there!\"}]"; // Przykładowa odpowiedź

        doReturn(ResponseEntity.ok(expectedResponse)).when(mailService).getReceivedMails(anyString());

        mockMvc.perform(get("/api/received-mails")
                        .param("email", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @WithMockUser
    @Test
    void testGetSentMails() throws Exception {
        String email = "test@example.com";
        String expectedResponse = "[{\"id\":1,\"to\":\"test@example.com\",\"subject\":\"Hello\",\"body\":\"Hi there!\"}]"; // Przykładowa odpowiedź

        doReturn(ResponseEntity.ok(expectedResponse)).when(mailService).getSentMails(anyString());

        mockMvc.perform(get("/api/sent-mails")
                        .param("email", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @WithMockUser
    @Test
    void testSetMailWasOpen() throws Exception {
        Long mailId = 1L;

        doNothing().when(mailService).setMailWasOpen(mailId);

        mockMvc.perform(put("/api/set/mail-read/{id}", mailId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
