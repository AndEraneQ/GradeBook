package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.GradeDto;
import com.troja.GradeBook.dto.requests.AddGradeRequest;
import com.troja.GradeBook.entity.Grade;
import com.troja.GradeBook.mapper.GradeMapper;
import com.troja.GradeBook.repository.GradeRepository;
import com.troja.GradeBook.repository.SubjectRepository;
import com.troja.GradeBook.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GradesService {

    private GradeRepository gradeRepository;
    private GradeMapper gradeMapper;
    private UserRepository userRepository;
    private SubjectRepository subjectRepository;

    public ResponseEntity<?> findByUserIdAndSubjectId(Long userId, Long subjectId) {
        List<Grade> grades = gradeRepository.findByUserIdAndSubjectId(userId,subjectId);
        return ResponseEntity.ok(grades
                .stream()
                .map(gradeMapper::toDto)
                .collect(Collectors.toList())
        );
    }

    public ResponseEntity<?> editGrade(GradeDto gradeDto) {
        Grade grade = gradeRepository.findById(gradeDto.getId()).orElseThrow();

        grade.setDate(new Date());
        grade.setValue(gradeDto.getValue());
        grade.setDescription(gradeDto.getDescription());

        gradeRepository.save(grade);

        return ResponseEntity.ok("Updated grade correctly!");
    }

    public ResponseEntity<?> addGrade(AddGradeRequest addGradeRequest) {
        Grade grade = new Grade();

        grade.setUser(userRepository.findById(addGradeRequest.getStudentId()).orElseThrow());
        grade.setSubject(subjectRepository.findById(addGradeRequest.getSubjectId()).orElseThrow());
        grade.setValue(addGradeRequest.getValue());
        grade.setDescription(addGradeRequest.getDescription());
        grade.setDate(new Date());

        gradeRepository.save(grade);

        return ResponseEntity.ok("Added grade Correctly");
    }

    public ResponseEntity<?> deleteGrade(Long gradeId) {
        gradeRepository.deleteById(gradeId);
        return ResponseEntity.ok("Deleted Grade Correctly");
    }
}
