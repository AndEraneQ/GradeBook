package com.troja.GradeBook.repository;

import com.troja.GradeBook.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends JpaRepository<Mail,Long> {
    List<Mail> findByFromUserEmail(String email);
    List<Mail> findByToUserEmail(String email);
}
