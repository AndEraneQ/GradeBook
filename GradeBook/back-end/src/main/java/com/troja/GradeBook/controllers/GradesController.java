package com.troja.GradeBook.controllers;

import com.troja.GradeBook.dto.GradeDto;
import com.troja.GradeBook.dto.requests.AddGradeRequest;
import com.troja.GradeBook.entity.Grade;
import com.troja.GradeBook.services.GradesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GradesController {
    private GradesService gradesService;

    @GetMapping("/grades/user/{userId}/subject/{subjectId}")
    public ResponseEntity<?> getGradesByUserIdAndSubjectId(
            @PathVariable("userId") Long userId,
            @PathVariable("subjectId") Long subjectId) {
        return gradesService.findByUserIdAndSubjectId(userId, subjectId);
    }

    @PutMapping("/edit/grade")
    public ResponseEntity<?> editGrade(@RequestBody GradeDto gradeDto){
        return gradesService.editGrade(gradeDto);
    }

    @PostMapping("/add/grade")
    public ResponseEntity<?> addGrade(@RequestBody AddGradeRequest addGradeRequest){
        return gradesService.addGrade(addGradeRequest);
    }

    @DeleteMapping("/delete/grade/{id}")
    public ResponseEntity<?> deleteGrade(@PathVariable("id")Long gradeId){
        return gradesService.deleteGrade(gradeId);
    }
}
