package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.MailDto;
import com.troja.GradeBook.dto.requests.SendMailRequest;
import com.troja.GradeBook.entity.Mail;
import com.troja.GradeBook.mapper.MailMapper;
import com.troja.GradeBook.repository.MailRepository;
import com.troja.GradeBook.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MailService {

    private MailRepository mailRepository;
    private MailMapper mailMapper;
    private UserRepository userRepository;

    public ResponseEntity<?> sendMail(SendMailRequest sendMailRequest){

        if(!userRepository.existsByEmail(sendMailRequest.getToUserEmail())){
            return ResponseEntity.badRequest().body("This user don't exists!");
        }

        Mail mail = new Mail();
        mail.setFromUser(userRepository.findByEmail(sendMailRequest.getFromUserEmail()).orElseThrow());
        mail.setToUser(userRepository.findByEmail(sendMailRequest.getToUserEmail()).orElseThrow());
        mail.setContent(sendMailRequest.getContent());
        mail.setSubject(sendMailRequest.getSubject());
        mail.setSentAt(LocalDateTime.now());

        mailRepository.save(mail);

        return ResponseEntity.ok("Message send correctly!");

    }

    public ResponseEntity<?> getReceivedMails(String email) {
        List<Mail> mailList = mailRepository.findByToUserEmail(email);
        List<MailDto> mailDtos = mailList.stream()
                .sorted(Comparator.comparing(Mail::getSentAt).reversed())
                .map(mailMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(mailDtos);
    }

    public ResponseEntity<?> getSentMails(String email) {
        List<Mail> mailList = mailRepository.findByFromUserEmail(email);
        List<MailDto> mailDtos = mailList.stream()
                .sorted(Comparator.comparing(Mail::getSentAt).reversed())
                .map(mailMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(mailDtos);
    }

    @Transactional
    public void setMailWasOpen(Long mailId){
        Mail mail = mailRepository.findById(mailId).orElseThrow();
        mail.setRead(true);
        mailRepository.save(mail);
    }
}
