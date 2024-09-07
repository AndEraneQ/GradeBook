package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.MailDto;
import com.troja.GradeBook.dto.requests.SendMailRequest;
import com.troja.GradeBook.entity.Mail;
import com.troja.GradeBook.entity.User;
import com.troja.GradeBook.exception.resource.ResourceNotFoundException;
import com.troja.GradeBook.mapper.MailMapper;
import com.troja.GradeBook.repository.MailRepository;
import com.troja.GradeBook.repository.UserRepository;
import com.troja.GradeBook.services.MailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MailServiceTest {

    @Mock
    private MailRepository mailRepository;

    @Mock
    private MailMapper mailMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MailService mailService;

    @Test
    public void testSendMail_Success() {
        SendMailRequest request = new SendMailRequest();
        request.setFromUserEmail("from@example.com");
        request.setToUserEmail("to@example.com");
        request.setContent("Hello, this is a test message!");
        request.setSubject("Test Subject");

        User fromUser = new User();
        fromUser.setEmail("from@example.com");

        User toUser = new User();
        toUser.setEmail("to@example.com");

        when(userRepository.findByEmail("from@example.com")).thenReturn(Optional.of(fromUser));
        when(userRepository.findByEmail("to@example.com")).thenReturn(Optional.of(toUser));

        ResponseEntity<?> response = mailService.sendMail(request);

        verify(mailRepository, times(1)).save(any(Mail.class));
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Message sent successfully!", response.getBody());
    }

    @Test
    public void testSendMail_FromUserNotFound() {
        SendMailRequest request = new SendMailRequest();
        request.setFromUserEmail("from@example.com");
        request.setToUserEmail("to@example.com");

        when(userRepository.findByEmail("to@example.com")).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> mailService.sendMail(request));

        assertEquals("400 BAD_REQUEST \"Recipient user does not exist.\"", exception.getMessage());
        verify(mailRepository, never()).save(any(Mail.class));
    }

    @Test
    public void testGetReceivedMails_Success() {
        String email = "to@example.com";
        Mail mail = new Mail();
        mail.setSentAt(LocalDateTime.now());

        when(mailRepository.findByToUserEmail(email)).thenReturn(List.of(mail));
        when(mailMapper.toDto(mail)).thenReturn(new MailDto());

        ResponseEntity<List<MailDto>> response = mailService.getReceivedMails(email);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(mailRepository, times(1)).findByToUserEmail(email);
    }

    @Test
    public void testGetSentMails_Success() {
        String email = "to@example.com";
        Mail mail = new Mail();
        mail.setSentAt(LocalDateTime.now());

        when(mailRepository.findByFromUserEmail(email)).thenReturn(List.of(mail));
        when(mailMapper.toDto(mail)).thenReturn(new MailDto());

        ResponseEntity<List<MailDto>> response = mailService.getSentMails(email);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(mailRepository, times(1)).findByFromUserEmail(email);
    }

    @Test
    public void testSetMailWasOpen_Success() {
        Mail mail = new Mail();
        mail.setId(1L);
        mail.setRead(false);

        when(mailRepository.findById(1L)).thenReturn(Optional.of(mail));

        mailService.setMailWasOpen(1L);

        assertTrue(mail.isRead());
        verify(mailRepository, times(1)).save(mail);
    }

    @Test
    public void testSetMailWasOpen_MailNotFound() {
        when(mailRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> mailService.setMailWasOpen(1L));

        assertEquals("Mail not found.", exception.getMessage());
        verify(mailRepository, never()).save(any(Mail.class));
    }
}
