package com.troja.GradeBook.services.IServices;

import com.troja.GradeBook.dto.MailDto;
import com.troja.GradeBook.dto.requests.SendMailRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMailService {
    ResponseEntity<?> sendMail(SendMailRequest sendMailRequest);
    ResponseEntity<List<MailDto>> getReceivedMails(String email);
    ResponseEntity<List<MailDto>> getSentMails(String email);
    void setMailWasOpen(Long mailId);
}
