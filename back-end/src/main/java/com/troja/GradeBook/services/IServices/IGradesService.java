package com.troja.GradeBook.services.IServices;

import com.troja.GradeBook.dto.GradeDto;
import com.troja.GradeBook.dto.requests.AddGradeRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGradesService {

    ResponseEntity<List<GradeDto>> findByUserIdAndSubjectId(Long userId, Long subjectId);

    ResponseEntity<String> editGrade(GradeDto gradeDto);

    ResponseEntity<String> addGrade(AddGradeRequest addGradeRequest);

    ResponseEntity<String> deleteGrade(Long gradeId);
}
