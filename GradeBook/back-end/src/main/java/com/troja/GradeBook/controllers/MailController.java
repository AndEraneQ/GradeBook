package com.troja.GradeBook.controllers;

import com.troja.GradeBook.dto.requests.SendMailRequest;
import com.troja.GradeBook.services.MailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MailController {

    private MailService mailService;

    @PostMapping("/send/mail")
    public ResponseEntity<?> sendMail(@RequestBody SendMailRequest sendMailRequest){
        return mailService.sendMail(sendMailRequest);
    }

    @GetMapping("/received-mails")
    public ResponseEntity<?> getReceivedMails(@RequestParam String email) {
        return mailService.getReceivedMails(email);
    }

    @GetMapping("/sent-mails")
    public ResponseEntity<?> getSentMails(@RequestParam String email) {
        return mailService.getSentMails(email);
    }

    @PutMapping("/set/mail-read/{id}")
    public void setMailWasOpen(@PathVariable("id")Long mailId){
        mailService.setMailWasOpen(mailId);
    }
}
