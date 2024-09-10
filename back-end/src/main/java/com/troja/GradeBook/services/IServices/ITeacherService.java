package com.troja.GradeBook.services.IServices;

import com.troja.GradeBook.dto.TeacherDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITeacherService {

    ResponseEntity<List<TeacherDto>> getAllTeachers();

    ResponseEntity<List<TeacherDto>> getAllTeachersOfSubject(Long subjectId);

    ResponseEntity<TeacherDto> getTeacherFromUserId(Long userId);
}
