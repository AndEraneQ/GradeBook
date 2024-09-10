package com.troja.GradeBook.services;

import com.troja.GradeBook.dto.GradeDto;
import com.troja.GradeBook.dto.requests.AddGradeRequest;
import com.troja.GradeBook.entity.Grade;
import com.troja.GradeBook.exception.resource.ResourceNotFoundException;
import com.troja.GradeBook.mapper.GradeMapper;
import com.troja.GradeBook.repository.GradeRepository;
import com.troja.GradeBook.repository.SubjectRepository;
import com.troja.GradeBook.repository.UserRepository;
import com.troja.GradeBook.services.IServices.IGradesService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GradesService implements IGradesService {

    private static final Logger logger = LoggerFactory.getLogger(GradesService.class);
    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public ResponseEntity<List<GradeDto>> findByUserIdAndSubjectId(Long userId, Long subjectId) {
        List<Grade> grades = gradeRepository.findByUserIdAndSubjectId(userId, subjectId);
        List<GradeDto> gradeDtos = grades.stream()
                .map(gradeMapper::toDto)
                .collect(Collectors.toList());

        logger.info("Fetched grades for userId: {} and subjectId: {}", userId, subjectId);
        return ResponseEntity.ok(gradeDtos);
    }

    @Override
    public ResponseEntity<String> editGrade(GradeDto gradeDto) {
        Grade grade = gradeRepository.findById(gradeDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Grade not found with id: " + gradeDto.getId()));

        grade.setDate(new Date());
        grade.setValue(gradeDto.getValue());
        grade.setDescription(gradeDto.getDescription());

        gradeRepository.save(grade);
        logger.info("Updated grade with id: {}", gradeDto.getId());
        return ResponseEntity.ok("Grade updated successfully!");
    }
    @Override

    public ResponseEntity<String> addGrade(AddGradeRequest addGradeRequest) {
        Grade grade = new Grade();

        grade.setUser(userRepository.findById(addGradeRequest.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + addGradeRequest.getStudentId())));
        grade.setSubject(subjectRepository.findById(addGradeRequest.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + addGradeRequest.getSubjectId())));
        grade.setValue(addGradeRequest.getValue());
        grade.setDescription(addGradeRequest.getDescription());
        grade.setDate(new Date());

        gradeRepository.save(grade);
        logger.info("Added new grade for studentId: {}", addGradeRequest.getStudentId());
        return ResponseEntity.status(HttpStatus.CREATED).body("Grade added successfully!");
    }

    @Override
    public ResponseEntity<String> deleteGrade(Long gradeId) {
        if (!gradeRepository.existsById(gradeId)) {
            throw new ResourceNotFoundException("Grade not found with id: " + gradeId);
        }
        gradeRepository.deleteById(gradeId);
        logger.info("Deleted grade with id: {}", gradeId);
        return ResponseEntity.ok("Grade deleted successfully!");
    }
}
