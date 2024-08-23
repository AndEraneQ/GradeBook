package com.troja.GradeBook.controllers;

import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.services.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teachers")
    public ResponseEntity<?> getAllTeachers(){
        return teacherService.getAllTeachers();
    }

    @GetMapping("/teachers/subject/{id}")
    public ResponseEntity<?> getAllTeachersOfSubject(@PathVariable("id") Long subjectId){
        return teacherService.getAllTeachersOfSubject(subjectId);
    }
}
