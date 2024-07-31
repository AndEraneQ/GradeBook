package com.troja.GradeBook.services;

import com.troja.GradeBook.entity.Subject;
import com.troja.GradeBook.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubjectService {

    private SubjectRepository subjectRepository;

    public List<Subject> getAllSubject(){
        return subjectRepository.findAll();
    }
}
