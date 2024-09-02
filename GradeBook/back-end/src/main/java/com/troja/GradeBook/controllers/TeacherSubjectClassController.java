package com.troja.GradeBook.controllers;

import com.troja.GradeBook.dto.TeacherSubjectClassDto;
import com.troja.GradeBook.services.TeacherSubjectClassService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TeacherSubjectClassController {
    private TeacherSubjectClassService teacherSubjectClassService;

    @GetMapping("/connection/class/{id}")
    public ResponseEntity<?> findConnectionByClassId(@PathVariable("id")Long classId){
        return teacherSubjectClassService.findConnectionByClassId(classId);
    }

    @PutMapping("/connection/update")
    public ResponseEntity<String> updateConnection(@RequestBody TeacherSubjectClassDto teacherSubjectClassDto){
        return teacherSubjectClassService.updateConnection(teacherSubjectClassDto);
    }

    @GetMapping("/teacher/{id}/classes-and-subjects")
    public ResponseEntity<?> getClassAndSubjectsOfTeacher(@PathVariable("id")Long teacherId){
        return teacherSubjectClassService.getAllClassesAndSubjectsLearningByTeacher(teacherId);
    }
}

