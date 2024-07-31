package com.troja.GradeBook.controllers;

import com.troja.GradeBook.entity.Subject;
import com.troja.GradeBook.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class SubjectController {

    private SubjectService subjectService;

    @GetMapping("/subjects")
    public List<Subject> getAllSubjects(){
        return subjectService.getAllSubject();
    }
}
