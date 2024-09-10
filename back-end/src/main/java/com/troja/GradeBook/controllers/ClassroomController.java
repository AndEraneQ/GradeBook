package com.troja.GradeBook.controllers;

import com.troja.GradeBook.dto.TeacherDto;
import com.troja.GradeBook.dto.requests.AddClassRequest;
import com.troja.GradeBook.dto.requests.EditClassRequest;
import com.troja.GradeBook.services.ClassroomService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ClassroomController {

    private ClassroomService classService;

    @GetMapping("/classes")
    public ResponseEntity<?> findAllClasses(){
        return classService.findAllClasses();
    }

    @PostMapping("/add/class")
    public ResponseEntity<?> addClass(@RequestBody @Valid AddClassRequest addClassRequest){
        return classService.addClass(addClassRequest.getClassName(),addClassRequest.getTeacher());
    }

    @GetMapping("/find/students/{id}")
    public ResponseEntity<?> findStudentsOfClass(@PathVariable("id") Long classId){
        return classService.findStudentsOfClass(classId);
    }

    @DeleteMapping("/delete/classroom/{id}")
    public ResponseEntity<?> deleteClass(@PathVariable("id") Long classId){
        return classService.deleteClass(classId);
    }

    @PutMapping("edit/classroom")
    public ResponseEntity<?> editClassroom(@RequestBody @Valid EditClassRequest editClassRequest){
        return classService.editClassroom(editClassRequest);
    }
}
