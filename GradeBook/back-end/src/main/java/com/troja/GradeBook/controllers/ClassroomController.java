package com.troja.GradeBook.controllers;

import com.troja.GradeBook.services.ClassroomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ClassroomController {

    private ClassroomService classService;

    @GetMapping("/classes")
    public ResponseEntity<?> findAllClasses(){
        return classService.findAllClasses();
    }
}
