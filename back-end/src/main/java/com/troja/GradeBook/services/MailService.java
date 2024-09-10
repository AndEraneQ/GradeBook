package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.MailDto;
import com.troja.GradeBook.dto.requests.SendMailRequest;
import com.troja.GradeBook.entity.Mail;
import com.troja.GradeBook.exception.resource.ResourceNotFoundException;
import com.troja.GradeBook.mapper.MailMapper;
import com.troja.GradeBook.repository.MailRepository;
import com.troja.GradeBook.repository.UserRepository;
import com.troja.GradeBook.services.IServices.IMailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MailService implements IMailService {

    private final MailRepository mailRepository;
    private final MailMapper mailMapper;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> sendMail(SendMailRequest sendMailRequest) {

        var toUser = userRepository.findByEmail(sendMailRequest.getToUserEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Recipient user does not exist."));

        var fromUser = userRepository.findByEmail(sendMailRequest.getFromUserEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender user not found."));

        Mail mail = Mail.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .content(sendMailRequest.getContent())
                .subject(sendMailRequest.getSubject())
                .sentAt(LocalDateTime.now())
                .build();

        mailRepository.save(mail);

        return ResponseEntity.ok("Message sent successfully!");
    }

    @Override
    public ResponseEntity<List<MailDto>> getReceivedMails(String email) {
        return ResponseEntity.ok(getMailsByEmail(email, true));
    }

    @Override
    public ResponseEntity<List<MailDto>> getSentMails(String email) {
        return ResponseEntity.ok(getMailsByEmail(email, false));
    }

    @Override
    @Transactional
    public void setMailWasOpen(Long mailId) {
        Mail mail = mailRepository.findById(mailId)
                .orElseThrow(() -> new ResourceNotFoundException("Mail not found."));
        mail.setRead(true);
        mailRepository.save(mail);
    }

    private List<MailDto> getMailsByEmail(String email, boolean isReceived) {
        List<Mail> mails = isReceived
                ? mailRepository.findByToUserEmail(email)
                : mailRepository.findByFromUserEmail(email);

        return mails.stream()
                .sorted(Comparator.comparing(Mail::getSentAt).reversed())
                .map(mailMapper::toDto)
                .collect(Collectors.toList());
    }
}

